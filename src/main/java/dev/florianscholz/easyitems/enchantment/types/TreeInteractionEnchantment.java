package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface TreeInteractionEnchantment extends IEnchantment {

    default boolean shouldFellTree(BlockState state) {
        return isLog(state.getBlock());
    }

    default int getMaxTreeSize(int level) {
        return 50 + (level * 10);
    }

    default boolean consumeDurabilityPerBlock(int level) {
        return true;
    }

    default float getBonusDropChance(Player player, int level) {
        return 0.1f * level;
    }

    default void onTreeBlock(Player player, BlockPos pos, BlockState state, int blockNumber, int level) {}

    default void onTreeFell(Player player, int totalBlocks, int level) {}

    default boolean isLog(Block block) {
        return block instanceof RotatedPillarBlock &&
                (block.defaultBlockState().is(BlockTags.LOGS) ||
                block.defaultBlockState().is(BlockTags.LOGS_THAT_BURN));
    }

    default boolean isLeaf(Block block) {
        return block.defaultBlockState().is(BlockTags.LEAVES);
    }

}
