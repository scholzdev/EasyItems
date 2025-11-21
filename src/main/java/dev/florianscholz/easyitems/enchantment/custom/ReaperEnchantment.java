package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class ReaperEnchantment extends BaseEnchantment {

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
}