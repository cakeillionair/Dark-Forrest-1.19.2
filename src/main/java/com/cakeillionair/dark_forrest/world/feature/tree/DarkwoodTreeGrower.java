package com.cakeillionair.dark_forrest.world.feature.tree;

import com.cakeillionair.dark_forrest.world.feature.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class DarkwoodTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return ModConfiguredFeatures.DARKWOOD.getHolder().get();
    }
}
