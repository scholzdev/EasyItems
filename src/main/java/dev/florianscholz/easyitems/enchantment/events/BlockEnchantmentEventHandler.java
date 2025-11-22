package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.world.entity.ai.behavior.TradeWithVillager;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.TradeWithVillagerEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class BlockEnchantmentEventHandler {

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        if(event.getPlayer().level().isClientSide()) return;
        ModEnchantmentRegistry.getAllBlock().forEach(blockEnchantment -> {
            int level = EnchantmentUtils.getLevel(event.getPlayer().getMainHandItem(), blockEnchantment.getKey());

            if(level <= 0) return;

            blockEnchantment.onBreakBlock(event.getPlayer(), event.getPos(), event.getPlayer().getMainHandItem(), level);
        });
    }

    @SubscribeEvent
    public static void onDrop(BlockDropsEvent event) {
        if(event.getLevel().isClientSide()) return;
        if(!(event.getBreaker() instanceof Player player)) return;

        ModEnchantmentRegistry.getAllBlock().forEach(blockEnchantment -> {
            int level = EnchantmentUtils.getLevel(event.getTool(), blockEnchantment.getKey());
            if(level <= 0) return;

            blockEnchantment.onDropBlock(event.getLevel(), player, event.getDrops(), event.getPos(), event.getTool(), level);
        });
    }

}
