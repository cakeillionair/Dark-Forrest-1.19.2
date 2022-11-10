package com.cakeillionair.dark_forrest.item;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.item.custom.HammerItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Dark_Forrest.MOD_ID);
    //standard
    public static final RegistryObject<Item> DARKWOOD_STICK = ITEMS.register("darkwood_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> RAW_DARKNESS = ITEMS.register("raw_darkness",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> MOLTEN_DARKNESS = ITEMS.register("molten_darkness",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> DARK_INGOT = ITEMS.register("dark_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> MOLTEN_NETHERITE = ITEMS.register("molten_netherite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> MOLTEN_COPPER = ITEMS.register("molten_copper",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> COATED_COPPER = ITEMS.register("coated_copper",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> UNPROCESSED_DARKWOOD_STICK = ITEMS.register("unprocessed_darkwood_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));

    //custom
    public static final RegistryObject<HammerItem> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
