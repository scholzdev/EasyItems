package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.DamageModifierEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ReaperEnchantment extends BaseEnchantment implements DamageModifierEnchantment {

    public ReaperEnchantment() {
        super(
                ModEnchantmentKeys.REAPER,
                ItemTags.HOES,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlot.MAINHAND,
                5,
                3,
                5,
                8,
                20,
                8,
                1
        );
    }

    @Override
    public float modifyOutgoingDamage(Player attacker, LivingEntity target, float originalDamage, int level, ItemStack weapon) {
        return 40;
    }
}