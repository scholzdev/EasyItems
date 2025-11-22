package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface CombatEnchantment extends IEnchantment {

    /**
     * Wird aufgerufen wenn der Träger Schaden zufügt
     */
    default void onAttack(Player attacker, LivingEntity target, int level, ItemStack weapon, float damage) {}

    default void onHurt(Player attacker, LivingEntity victim, int level, float damage) {}

    /**
     * Wird aufgerufen nach dem Angriff (auch wenn kein Schaden)
     */
    default void postAttack(Player attacker, LivingEntity target, int level, ItemStack weapon) {}
}