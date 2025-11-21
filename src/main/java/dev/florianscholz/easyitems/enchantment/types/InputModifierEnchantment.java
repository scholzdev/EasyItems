package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.entity.player.Player;

public interface InputModifierEnchantment extends IEnchantment {
    default void onInput(Player player, int key, int action) {}
    default void onMouseButton(Player player, int button, int action) {}
    default void onMouseScroll(Player player, double mouseX, double mouseY, boolean leftDown, boolean middleDown, boolean rightDown, double scrollDeltaX, double scrollDeltaY) {}
}
