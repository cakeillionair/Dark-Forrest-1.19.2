package com.cakeillionair.dark_forrest.world.feature;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Dark_Forrest.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?,?>> DARKWOOD =
            CONFIGURED_FEATURES.register("darkwood",() ->
                    //TODO : FIX UGLY AS F***
                    new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                            BlockStateProvider.simple(ModBlocks.DARKWOOD_LOG.get()),
                            new ForkingTrunkPlacer(10,2,2),
                            BlockStateProvider.simple(ModBlocks.DARKWOOD_LEAVES.get()),
                            new AcaciaFoliagePlacer(ConstantInt.of(2),ConstantInt.of(0)),
                            new TwoLayersFeatureSize(1,0,2)).build()));

    public static final RegistryObject<ConfiguredFeature<?,?>> DARKWOOD_SPAWN =
            CONFIGURED_FEATURES.register("darkwood_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(
                            ModPlacedFeatures.DARKWOOD_CHECKED.getHolder().get(),
                            0.5F)), ModPlacedFeatures.DARKWOOD_CHECKED.getHolder().get())));

    public static void register(IEventBus eventBus){CONFIGURED_FEATURES.register(eventBus);}
}
