package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class XPEnchantmentEventHandler {

    @SubscribeEvent
    public static void onPickup(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();

        ModEnchantmentRegistry.getAllXP().forEach(enchantment -> {
            enchantment.onXpChange(player, event.getAmount());

            int multiplier = enchantment.getXpMultiplier(player);
            int modifier = enchantment.getXPModifier(player);

            int oldAmount = event.getAmount();
            int newAmount = (oldAmount + modifier) * multiplier;

            event.setAmount(newAmount);
        });
    }

}
