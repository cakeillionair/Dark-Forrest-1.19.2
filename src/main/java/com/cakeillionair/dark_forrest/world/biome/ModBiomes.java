package com.cakeillionair.dark_forrest.world.biome;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Dark_Forrest.MOD_ID);

    //TODO : MAKE BIOME CUSTOM
    public static final RegistryObject<Biome> DF_BIOME = BIOMES.register("df_biome",
            () -> /*new Biome.BiomeBuilder().build()*/OverworldBiomes.darkForest());
}
