package com.cakeillionair.dark_forrest.item;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Dark_Forrest.MOD_ID);

    public static final RegistryObject<Item> DARKWOOD_STICK = ITEMS.register("darkwood_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> RAW_DARKNESS = ITEMS.register("raw_darkness",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
