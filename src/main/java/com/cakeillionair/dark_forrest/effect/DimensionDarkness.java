package com.cakeillionair.dark_forrest.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.RegistryObject;

public class DimensionDarkness {
    //TODO : ?????????????????
    public static final RegistryObject<MobEffect> DARKNESS = ModEffects.MOB_EFFECTS.register("dim_darkness",
            () -> MobEffects.DARKNESS);
}
