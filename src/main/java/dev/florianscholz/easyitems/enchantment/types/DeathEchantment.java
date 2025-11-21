package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface DeathEchantment extends IEnchantment{
    void onEntityKilled(Player player, Entity victim, ItemStack weapon, int level, Level serverLevel);
}
