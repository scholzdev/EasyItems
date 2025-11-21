package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ProjectileEnchantment extends IEnchantment {

    default void onProjectileFired(Player shooter, ItemStack weapon, int level){}
    default void onProjectileHit(Player shooter, Entity target, int level){}

}
