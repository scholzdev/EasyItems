package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.enchantment.types.TickableEnchantment;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class TickableEnchantmentEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        for(TickableEnchantment enchantment : ModEnchantmentRegistry.getAllTickable()) {
            tickEnchantment(player, enchantment);
        }
    }

    private static void tickEnchantment(Player player, TickableEnchantment enchantment) {
        if(player.tickCount % enchantment.getTickInterval() != 0) return;

        EquipmentSlot slot = enchantment.getSlot();
        ItemStack stack = player.getItemBySlot(slot);
        int level = EnchantmentUtils.getLevel(stack, enchantment.getKey());
        if(level <= 0) return;
        enchantment.onTick(player, level, stack, slot);

    }

}
