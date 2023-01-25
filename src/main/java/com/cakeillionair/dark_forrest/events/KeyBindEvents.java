package com.cakeillionair.dark_forrest.events;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class KeyBindEvents {
    @Mod.EventBusSubscriber(modid = Dark_Forrest.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key e){
            if(KeyBinding.WARP_KEY.consumeClick()){
                /* TODO WTF FIX
                LocalPlayer p = Minecraft.getInstance().player;
                Vec3 l = p.getLookAngle().normalize();
                Vec3 pos = p.getPosition(1);
                pos.add(l.multiply(5,5,5));
                p.teleportTo(pos.x,pos.y,pos.z);
                */
            }
        }
    }
    @Mod.EventBusSubscriber(modid = Dark_Forrest.MOD_ID,value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvent {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent e){
            e.register(KeyBinding.WARP_KEY);
        }
    }
}
