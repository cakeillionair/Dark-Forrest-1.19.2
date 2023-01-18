package com.cakeillionair.dark_forrest.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class DarkwoodLeaves extends Block {
    public static final BooleanProperty BERRIES = BooleanProperty.create("berries");

    public DarkwoodLeaves(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult blockHitResult) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            level.setBlock(blockPos, state.cycle(BERRIES), 3);
        }

        return super.use(state, level, blockPos, player, hand, blockHitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BERRIES);
    }
}
