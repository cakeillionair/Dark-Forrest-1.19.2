package com.cakeillionair.dark_forrest.item.custom;

import com.cakeillionair.dark_forrest.block.ModBlocks;
import com.cakeillionair.dark_forrest.block.custom.DFPortalBlock;
import com.cakeillionair.dark_forrest.item.ModCreativeModeTab;
import com.cakeillionair.dark_forrest.world.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CatalystItem extends Item {
    public CatalystItem() {
        super(new Properties()
                .tab(ModCreativeModeTab.DARK_FORREST)
                .stacksTo(1)
                .rarity(Rarity.RARE)
        );
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        if(context.getPlayer() != null) {
            if(context.getPlayer().level.dimension() == ModDimensions.DIM_KEY
                    || context.getPlayer().level.dimension() == Level.OVERWORLD) {
                for(Direction direction : Direction.Plane.VERTICAL) {
                    BlockPos framePos = context.getClickedPos().relative(direction);
                    if(((DFPortalBlock) ModBlocks.FORREST_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos)) {
                        context.getLevel().playSound(context.getPlayer(), framePos,
                                SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0F, 1.0F);
                        return InteractionResult.CONSUME;
                    }
                    else return InteractionResult.FAIL;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}
