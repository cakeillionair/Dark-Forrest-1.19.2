package com.cakeillionair.dark_forrest.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class PoisonMossBlock extends Block {
    public PoisonMossBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 160));
        }

        super.stepOn(level, blockPos, blockState, entity);
    }
}
