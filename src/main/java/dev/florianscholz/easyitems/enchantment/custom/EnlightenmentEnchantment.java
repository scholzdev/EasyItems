package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class EnlightenmentEnchantment extends BaseEnchantment {

    public EnlightenmentEnchantment() {
        super(
                ModEnchantmentKeys.ENLIGHTENMENT,
                ItemTags.HEAD_ARMOR_ENCHANTABLE,
                EquipmentSlotGroup.HEAD,
                EquipmentSlot.HEAD,
                5,
                3,
                5,
                8,
                20,
                8,
                1
        );
    }
}
