package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class GourmetEnchantment extends BaseEnchantment {

    public GourmetEnchantment() {
        super(
                ModEnchantmentKeys.GOURMET,
                ItemTags.CHEST_ARMOR_ENCHANTABLE,
                EquipmentSlotGroup.CHEST,
                EquipmentSlot.CHEST,
                5,
                1,
                5,
                8,
                20,
                8,
                1
        );
    }
}
