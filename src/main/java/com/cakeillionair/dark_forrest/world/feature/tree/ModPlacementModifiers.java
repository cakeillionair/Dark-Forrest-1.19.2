package com.cakeillionair.dark_forrest.world.feature.tree;

import com.cakeillionair.dark_forrest.Dark_Forrest;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ModPlacementModifiers {
    public static PlacementModifierType<?> PLACEMENT_MODIFIER_TYPE;
    public static final PlacementModifier PLACEMENT_MODIFIER = new PlacementModifier() {
        @Override
        public Stream<BlockPos> getPositions(PlacementContext p_226389_, RandomSource p_226390_, BlockPos p_226391_) {
            int i = p_226391_.getX();
            int j = p_226391_.getZ();
            ArrayList<Integer> k_list = new ArrayList<>();
            for(int l = VerticalAnchor.aboveBottom(1).resolveY(p_226389_); l<VerticalAnchor.top().resolveY(p_226389_); l++){
                if(p_226389_.getBlockState(new BlockPos(i,j,l)).is(Blocks.AIR)&&p_226389_.getBlockState(new BlockPos(i,j,l).below()).is(BlockTags.DIRT)){
                    k_list.add(l);
                }
            }
            int k = k_list.get(p_226390_.nextIntBetweenInclusive(0, k_list.size()-1));
            return k > p_226389_.getMinBuildHeight() ? Stream.of(new BlockPos(i, k, j)) : Stream.of();
        }

        @Override
        public PlacementModifierType<?> type() {
            return PLACEMENT_MODIFIER_TYPE;
        }
    };
    public static void register(){
         PLACEMENT_MODIFIER_TYPE = BuiltinRegistries.register(Registry.PLACEMENT_MODIFIERS, Dark_Forrest.MOD_ID, () -> {
            return PlacementModifier.CODEC;
         }).get();
    }
}
