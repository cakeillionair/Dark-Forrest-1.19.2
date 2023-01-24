package com.cakeillionair.dark_forrest.world.feature;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Dark_Forrest.MOD_ID);

    public static final RegistryObject<PlacedFeature> DARK_ORE_PLACED = PLACED_FEATURES.register("dark_ore_placed",
            ()-> new PlacedFeature(ModConfiguredFeatures.DARK_ORE.getHolder().get(),
                    commonOrePlacement(7,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80),VerticalAnchor.aboveBottom(80)))));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
    //////////////
    //// TREE ////
    //////////////
    public static final RegistryObject<PlacedFeature> DARKWOOD_CHECKED = PLACED_FEATURES.register("darkwood_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.DARKWOOD.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.DARKWOOD_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> DARKWOOD_PLACED = PLACED_FEATURES.register("darkwood_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.DARKWOOD_SPAWN.getHolder().get(), /*VegetationPlacements.treePlacement(PlacementUtils.countExtra(3,0.1f,2))*/
                    ImmutableList.<PlacementModifier>builder()
                    .add(PlacementUtils.countExtra(3,0.1f,2))
                    .add(InSquarePlacement.spread())
                    .add(SurfaceWaterDepthFilter.forMaxDepth(0))
                    //////////////////////////////////////////////////////////////////////////////
                    //// THIS IS A WARNING FOR THE FUTURE: DON'T MAKE CUSTOM THINGS LIKE THIS ////
                    //////////////////////////////////////////////////////////////////////////////
                    //.add(HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM,VerticalAnchor.TOP)))
                    //.add(ModPlacementModifiers.PLACEMENT_MODIFIER)
                    .add(CountOnEveryLayerPlacement.of(1))
                    .add(BiomeFilter.biome()).build()));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
