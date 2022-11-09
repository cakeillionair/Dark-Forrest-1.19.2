package com.cakeillionair.dark_forrest.events;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Dark_Forrest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class ModServerEvent {

}
