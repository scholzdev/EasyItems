package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.entity.player.Player;

public interface XPEnchantment extends IEnchantment {

    default void onXpChange(Player player, int amount) {}

    default int getXpMultiplier(Player player) {
        return 1;
    }

    default int getXPModifier(Player player) {
        return 0;
    }
}
