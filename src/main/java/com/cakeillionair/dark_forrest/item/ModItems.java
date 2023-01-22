package com.cakeillionair.dark_forrest.item;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.ModBlocks;
import com.cakeillionair.dark_forrest.entity.ModEntityTypes;
import com.cakeillionair.dark_forrest.item.custom.CatalystItem;
import com.cakeillionair.dark_forrest.item.custom.HammerItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"unused"})
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
    public static final RegistryObject<Item> DARK_EGGPLANT_SEEDS = ITEMS.register("dark_eggplant_seeds",
            () -> new ItemNameBlockItem(ModBlocks.DARK_EGGPLANT_CROP.get(),
                    new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> DARK_EGGPLANT = ITEMS.register("dark_eggplant",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)
                    .food(new FoodProperties.Builder().nutrition(8).saturationMod(1.2f).build())));
    public static final RegistryObject<Item> DARK_WHALE_SPAWN_EGG = ITEMS.register("dark_whale_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.DARK_WHALE, 0x46085c, 0x00eaff,
                    new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));

    //custom
    public static final RegistryObject<HammerItem> HAMMER = ITEMS.register("hammer",
            () -> new HammerItem(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)));
    public static final RegistryObject<Item> CATALYST = ITEMS.register("catalyst", CatalystItem::new);
    public static final RegistryObject<Item> DARK_BERRIES = ITEMS.register("dark_berries",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.DARK_FORREST)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 600), 1f).build())));

    public static class Tiers {
        //TODO : REPLACE
        //public static final Tier EXAMPLE = new ForgeTier(2,800,1.5f,3,350, null, () -> Ingredient.of(Items.ACACIA_BOAT));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
