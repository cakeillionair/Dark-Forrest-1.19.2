package com.cakeillionair.dark_forrest.block;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import com.cakeillionair.dark_forrest.block.custom.DFPortalBlock;
import com.cakeillionair.dark_forrest.block.custom.ModFlammableRotatedPillarBlock;
import com.cakeillionair.dark_forrest.block.custom.PoisonMossBlock;
import com.cakeillionair.dark_forrest.item.ModCreativeModeTab;
import com.cakeillionair.dark_forrest.item.ModItems;
import com.cakeillionair.dark_forrest.world.feature.tree.DarkwoodTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@SuppressWarnings({"unused"})
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
    ///////////////////////
    //// DARKWOOD TREE ////
    ///////////////////////
    public static final RegistryObject<Block> DARKWOOD_LOG = registerBlock("darkwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)
                    .requiresCorrectToolForDrops()),ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> DARKWOOD_WOOD = registerBlock("darkwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)
                    .requiresCorrectToolForDrops()),ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> STRIPPED_DARKWOOD_LOG = registerBlock("stripped_darkwood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)
                    .requiresCorrectToolForDrops()),ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> STRIPPED_DARKWOOD_WOOD = registerBlock("stripped_darkwood_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)
                    .requiresCorrectToolForDrops()),ModCreativeModeTab.DARK_FORREST);

    public static final RegistryObject<Block> DARKWOOD_PLANKS = registerBlock("darkwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
                    .requiresCorrectToolForDrops()){
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            },ModCreativeModeTab.DARK_FORREST);
    public static final RegistryObject<Block> DARKWOOD_LEAVES = registerBlock("darkwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.AZALEA_LEAVES)){
                public static final BooleanProperty BERRIES = BooleanProperty.create("berries");

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult blockHitResult) {
                    if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND && state.getValue(BERRIES)) {
                        level.setBlock(blockPos, state.cycle(BERRIES), 3);
                        popResource(level, blockPos, new ItemStack(ModItems.DARK_BERRIES.get(), 1));
                    }

                    return super.use(state, level, blockPos, player, hand, blockHitResult);
                }
                @Override
                protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
                    builder.add(BERRIES,DISTANCE, PERSISTENT, WATERLOGGED);
                }
            }, ModCreativeModeTab.DARK_FORREST);

    public static final RegistryObject<Block> DARKWOOD_SAPLING = registerBlock("darkwood_sapling",
            () -> new SaplingBlock(new DarkwoodTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)),ModCreativeModeTab.DARK_FORREST);
    ///////////////////////////
    //// DARKWOOD TREE END ////
    ///////////////////////////
    //custom
    public static final RegistryObject<PoisonMossBlock> POISON_MOSS = registerBlock(
            "poison_moss", () -> new PoisonMossBlock(BlockBehaviour.Properties.of(
                    Material.MOSS).strength(0.1f).sound(SoundType.MUD)),
            ModCreativeModeTab.DARK_FORREST);

    public static final RegistryObject<Block> FORREST_PORTAL = registerBlockWithoutBlockItem("forrest_portal",
            DFPortalBlock::new);

    public static <T extends Block>RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block){
        return BLOCKS.register(name, block);
    }

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
