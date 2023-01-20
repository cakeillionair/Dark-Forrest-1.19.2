package com.cakeillionair.dark_forrest.block.custom;

import com.cakeillionair.dark_forrest.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(ModBlocks.DARKWOOD_LOG.get())){
                return ModBlocks.STRIPPED_DARKWOOD_LOG.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
            if(state.is(ModBlocks.DARKWOOD_WOOD.get())){
                return ModBlocks.STRIPPED_DARKWOOD_WOOD.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
