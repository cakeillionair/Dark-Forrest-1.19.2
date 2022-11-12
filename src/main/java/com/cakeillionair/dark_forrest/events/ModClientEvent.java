package com.cakeillionair.dark_forrest.events;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dark_Forrest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvent {
    @SubscribeEvent
    public static void lightningHammer(AttackEntityEvent e){
        if(e.getEntity().getMainHandItem().getItem()== ModItems.HAMMER.get()){
            LightningBolt lightning = new LightningBolt(EntityType.LIGHTNING_BOLT,e.getEntity().level);
            lightning.setPos(e.getTarget().getX(), e.getTarget().getY(), e.getTarget().getZ());
            e.getEntity().level.addFreshEntity(lightning);
        }
    }
}
