package com.cakeillionair.dark_forrest.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab DARK_FORREST = new CreativeModeTab("dark_forrest") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_DARKNESS.get());
        }
    };
}
