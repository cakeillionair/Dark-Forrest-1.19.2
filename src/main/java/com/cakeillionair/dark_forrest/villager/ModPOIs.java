package com.cakeillionair.dark_forrest.villager;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.ModBlocks;
import com.google.common.collect.ImmutableSet;

import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPOIs {
    public static final DeferredRegister<PoiType> POI
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, Dark_Forrest.MOD_ID);

    public static final RegistryObject<PoiType> FORREST_PORTAL =
            POI.register("forrest_portal", () -> new PoiType(
                    ImmutableSet.copyOf(ModBlocks.FORREST_PORTAL.get().getStateDefinition().getPossibleStates())
                    , 0, 1));


    public static void register(IEventBus eventBus) {
        POI.register(eventBus);
    }
}
