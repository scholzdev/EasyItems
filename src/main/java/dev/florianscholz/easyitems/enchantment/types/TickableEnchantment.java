package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface TickableEnchantment extends IEnchantment {

    void onTick(Player player, int level, ItemStack stack, EquipmentSlot slot);

    default int getTickInterval() {
        return 20;
    }
}