package com.cakeillionair.dark_forrest.events;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.ModBlocks;
import com.cakeillionair.dark_forrest.entity.ModEntityTypes;
import com.cakeillionair.dark_forrest.entity.custom.DarkWhaleEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ModServerEvent {
    @Mod.EventBusSubscriber(modid = Dark_Forrest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void darkwoodBoneMeal(BonemealEvent e){
            if(e.getLevel().getBlockState(e.getPos()).is(ModBlocks.DARKWOOD_SAPLING.get())){
                if(((SaplingBlock) e.getLevel().getBlockState(e.getPos()).getBlock()).isBonemealSuccess(e.getLevel(), RandomSource.create(),e.getPos(),e.getBlock())){
                    ((SaplingBlock) e.getLevel().getBlockState(e.getPos()).getBlock()).advanceTree(e.getLevel().getServer().getLevel(e.getLevel().dimension()),e.getPos(),e.getBlock(),RandomSource.create());
                }
                e.setCanceled(true);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Dark_Forrest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.DARK_WHALE.get(), DarkWhaleEntity.setAttributes());
        }
    }
}
