package dev.florianscholz.easyitems.enchantment.types.mobs;

import dev.florianscholz.easyitems.enchantment.types.IEnchantment;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface MobEffectEnchantment extends IEnchantment {

    default void onEffectAttack(Player player, LivingEntity target, int level, ItemStack weapon, float damage){}
    default boolean onEffectImmunityCheck(Player player, MobEffectInstance effectInstance) {
        return false;
    }

    default List<Holder<MobEffect>> getHandledEffects() {
        return null;
    }

    default void onEffectTick(Player player, MobEffectInstance effect) {}
}
