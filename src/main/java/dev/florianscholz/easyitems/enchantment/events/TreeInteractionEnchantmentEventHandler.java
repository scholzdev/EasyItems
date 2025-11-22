package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.enchantment.types.TreeInteractionEnchantment;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.*;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class TreeInteractionEnchantmentEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (player.level().isClientSide) return;

        ItemStack tool = player.getMainHandItem();
        BlockState state = event.getState();
        BlockPos startPos = event.getPos();

        ModEnchantmentRegistry.getAllTree().forEach(enchantment -> {
            int level = EnchantmentUtils.getLevel(tool, enchantment.getKey());
            if (level <= 0) return;

            if (!enchantment.shouldFellTree(state)) return;

            Set<BlockPos> tree = new HashSet<>();
            Set<BlockPos> leaves = new HashSet<>();

            findTree(player.level(), startPos, tree, leaves,
                    enchantment.getMaxTreeSize(level), enchantment);

            if (tree.size() <= 1) return;

            event.setCanceled(true);

            int blocksFelled = 0;
            for (BlockPos pos : tree) {
                BlockState logState = player.level().getBlockState(pos);

                Block.dropResources(logState, player.level(), pos, null, player, tool);

                if (player.level().random.nextFloat() < enchantment.getBonusDropChance(player, level)) {
                    addBonusDrops(player.level(), pos, logState);
                }

                player.level().destroyBlock(pos, false);

                if (enchantment.consumeDurabilityPerBlock(level)) {
                    tool.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    if (tool.isEmpty()) break;
                }

                enchantment.onTreeBlock(player, pos, logState, blocksFelled, level);
                blocksFelled++;
            }

            if (level >= 2) {
                for (BlockPos leafPos : leaves) {
                    if (leaves.size() > 200) break;

                    BlockState leafState = player.level().getBlockState(leafPos);
                    Block.dropResources(leafState, player.level(), leafPos, null, player, tool);
                    player.level().destroyBlock(leafPos, false);
                }
            }

            enchantment.onTreeFell(player, blocksFelled, level);

            player.level().playSound(null, startPos,
                    SoundEvents.WOOD_BREAK, SoundSource.BLOCKS,
                    1.0f, 0.8f);
        });
    }

    private static void findTree(Level level, BlockPos start, Set<BlockPos> logs,
                                 Set<BlockPos> leaves, int maxSize,
                                 TreeInteractionEnchantment enchant) {
        if (logs.size() >= maxSize) return;

        Queue<BlockPos> toCheck = new LinkedList<>();
        toCheck.add(start);
        logs.add(start);

        while (!toCheck.isEmpty() && logs.size() < maxSize) {
            BlockPos current = toCheck.poll();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos neighbor = current.offset(x, y, z);
                        Block block = level.getBlockState(neighbor).getBlock();

                        if (enchant.isLog(block)) {
                            if (logs.add(neighbor)) {
                                toCheck.add(neighbor);
                            }
                        } else if (enchant.isLeaf(block)) {
                            leaves.add(neighbor);
                        }
                    }
                }
            }
        }
    }

    private static void addBonusDrops(Level level, BlockPos pos, BlockState state) {
        if (level.random.nextFloat() < 0.5f) {
            Block.popResource(level, pos, new ItemStack(Items.STICK, 1 + level.random.nextInt(3)));
        }

        Item sapling = getSaplingForLog(state.getBlock());
        if (sapling != null && level.random.nextFloat() < 0.2f) {
            Block.popResource(level, pos, new ItemStack(sapling));
        }
    }

    private static Item getSaplingForLog(Block log) {
        if (log == Blocks.OAK_LOG || log == Blocks.OAK_WOOD) return Items.OAK_SAPLING;
        if (log == Blocks.BIRCH_LOG || log == Blocks.BIRCH_WOOD) return Items.BIRCH_SAPLING;
        if (log == Blocks.SPRUCE_LOG || log == Blocks.SPRUCE_WOOD) return Items.SPRUCE_SAPLING;
        if (log == Blocks.JUNGLE_LOG || log == Blocks.JUNGLE_WOOD) return Items.JUNGLE_SAPLING;
        if (log == Blocks.ACACIA_LOG || log == Blocks.ACACIA_WOOD) return Items.ACACIA_SAPLING;
        if (log == Blocks.DARK_OAK_LOG || log == Blocks.DARK_OAK_WOOD) return Items.DARK_OAK_SAPLING;
        if (log == Blocks.CHERRY_LOG || log == Blocks.CHERRY_WOOD) return Items.CHERRY_SAPLING;
        if (log == Blocks.MANGROVE_LOG || log == Blocks.MANGROVE_WOOD) return Items.MANGROVE_PROPAGULE;
        return null;
    }
}
