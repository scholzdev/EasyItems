package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public interface BlockEnchantment extends IEnchantment {

    void onBreakBlock(Player player, BlockPos pos, ItemStack tool, int level);

    void onDropBlock(Level worldLevel, Player breaker, List<ItemEntity> drops, BlockPos pos, ItemStack tool, int level);
}
