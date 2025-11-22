package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.XPEnchantment;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;

public class ProdigyEnchantment extends BaseEnchantment implements XPEnchantment {

    public ProdigyEnchantment() {
        super(
                ModEnchantmentKeys.PRODIGY,
                ItemTags.HEAD_ARMOR_ENCHANTABLE,
                EquipmentSlotGroup.HEAD,
                EquipmentSlot.HEAD,
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
    public int getXPModifier(Player player) {
        return 100;
    }


}