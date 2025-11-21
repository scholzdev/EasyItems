package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.enchantment.types.InputModifierEnchantment;
import dev.florianscholz.easyitems.enchantment.types.TickableEnchantment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class InputModifierEnchantmentEventHandler {

    @SubscribeEvent
    public static void onInputKey(InputEvent.Key event) {
        for(InputModifierEnchantment enchantment : ModEnchantmentRegistry.getAllInput()) {
            enchantment.onInput(Minecraft.getInstance().player, event.getKey(), event.getAction());
        }
    }

    @SubscribeEvent
    public static void onMouseButton(InputEvent.MouseButton.Post event) {
        for(InputModifierEnchantment enchantment : ModEnchantmentRegistry.getAllInput()) {
            enchantment.onMouseButton(Minecraft.getInstance().player, event.getButton(), event.getAction());
        }
    }

    @SubscribeEvent
    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        for(InputModifierEnchantment enchantment : ModEnchantmentRegistry.getAllInput()) {
            enchantment.onMouseScroll(Minecraft.getInstance().player, event.getMouseX(), event.getMouseY(), event.isLeftDown(), event.isMiddleDown(), event.isRightDown(), event.getScrollDeltaX(), event.getScrollDeltaY());
        }
    }

}


