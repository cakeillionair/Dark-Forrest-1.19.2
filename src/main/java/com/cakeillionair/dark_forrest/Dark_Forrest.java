package com.cakeillionair.dark_forrest;

import com.cakeillionair.dark_forrest.block.ModBlocks;
import com.cakeillionair.dark_forrest.effect.ModEffects;
import com.cakeillionair.dark_forrest.item.ModItems;
import com.cakeillionair.dark_forrest.villager.ModPOIs;
import com.cakeillionair.dark_forrest.world.dimension.ModDimensions;
import com.cakeillionair.dark_forrest.world.feature.ModConfiguredFeatures;
import com.cakeillionair.dark_forrest.world.feature.ModPlacedFeatures;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Mod(Dark_Forrest.MOD_ID)
public class Dark_Forrest
{
    public static final String MOD_ID = "dark_forrest";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Dark_Forrest() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModDimensions.register();

        ModPOIs.register(modEventBus);

        ModEffects.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(@SuppressWarnings("unused") final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SuppressWarnings("EmptyMethod")
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
