package com.cakeillionair.dark_forrest.world.dimension;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
    public static final ResourceKey<Level> DIM_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(Dark_Forrest.MOD_ID, "dim"));
    @SuppressWarnings("unused")
    public static final ResourceKey<DimensionType> DIM_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, DIM_KEY.registry());

    public static void register(){
        System.out.println("Registering Dimensions for " + Dark_Forrest.MOD_ID);
    }
}
