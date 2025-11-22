package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.CombatEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class VenomEnchantment extends BaseEnchantment implements CombatEnchantment {

    public VenomEnchantment() {
        super(
                ModEnchantmentKeys.VENOM,
                ItemTags.SWORDS,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlot.MAINHAND,
                5,
                1,
                5,
                8,
                20,
                8,
                1
        );
    }

    @Override
    public void onAttack(Player attacker, LivingEntity target, int level, ItemStack weapon, float damage) {
        target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, level));
        CombatEnchantment.super.onAttack(attacker, target, level, weapon, damage);
    }
}
