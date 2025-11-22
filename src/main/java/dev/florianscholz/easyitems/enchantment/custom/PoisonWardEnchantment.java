package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.mobs.effects.PoisonEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;

public class PoisonWardEnchantment extends BaseEnchantment implements PoisonEnchantment {

    public PoisonWardEnchantment() {
        super(
                ModEnchantmentKeys.POISON_WARD,
                ItemTags.SWORD_ENCHANTABLE,
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
    public boolean onEffectImmunityCheck(Player player, MobEffectInstance effectInstance) {
        return true;
    }
}
