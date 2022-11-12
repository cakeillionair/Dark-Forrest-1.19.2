package com.cakeillionair.dark_forrest.block;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.custom.DarkwoodLeaves;
import com.cakeillionair.dark_forrest.block.custom.PoisonMossBlock;
import com.cakeillionair.dark_forrest.item.ModCreativeModeTab;
import com.cakeillionair.dark_forrest.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Dark_Forrest.MOD_ID);
    //standard
    public static final RegistryObject<Block> BLOCK_OF_DARKNESS = registerBlock(
            "block_of_darkness", () -> new Block(BlockBehaviour.Properties.of(
                    Material.AMETHYST).strength(12f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)),
            ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> DARK_ORE = registerBlock(
            "dark_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(
                    Material.STONE).strength(8f).requiresCorrectToolForDrops(),
                    UniformInt.of(4, 8)),
            ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> DEEPSLATE_DARK_ORE = registerBlock(
            "deepslate_dark_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(
                    Material.STONE).strength(10f).requiresCorrectToolForDrops(),
                    UniformInt.of(6, 12)),
            ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> DARKWOOD_LOG = registerBlock(
            "darkwood_log", () -> new Block(BlockBehaviour.Properties.of(
                    Material.WOOD)
                    .strength(1f)
                    .sound(SoundType.WOOD)),
            ModCreativeModeTab.DARK_FORREST);

    //custom
    public static final RegistryObject<PoisonMossBlock> POISON_MOSS = registerBlock(
            "poison_moss", () -> new PoisonMossBlock(BlockBehaviour.Properties.of(
                    Material.MOSS).strength(0.1f).sound(SoundType.MUD)),
            ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<DarkwoodLeaves> DARKWOOD_LEAVES = registerBlock(
            "darkwood_leaves", () -> new DarkwoodLeaves(BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.1f)
                    .sound(SoundType.AZALEA_LEAVES)),
            ModCreativeModeTab.DARK_FORREST);

    public static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
