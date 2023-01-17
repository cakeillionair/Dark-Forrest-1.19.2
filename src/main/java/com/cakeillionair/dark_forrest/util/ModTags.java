package com.cakeillionair.dark_forrest.util;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@SuppressWarnings("unused")
public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> PORTAL_FRAME_BLOCKS
                    = tag("portal_frame_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Dark_Forrest.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Dark_Forrest.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
