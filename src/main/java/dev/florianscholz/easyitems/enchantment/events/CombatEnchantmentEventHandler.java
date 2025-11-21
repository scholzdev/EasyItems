package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.enchantment.types.CombatEnchantment;
import dev.florianscholz.easyitems.tracker.PaybackTracker;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class CombatEnchantmentEventHandler {

    @SubscribeEvent
    public static void onDamagePre(LivingDamageEvent.Pre event) {

        if(!(event.getSource().getEntity() instanceof  Player attacker)) return;

        ModEnchantmentRegistry.getAllCombat().forEach(combat -> {
            int level = EnchantmentUtils.getLevel(attacker.getMainHandItem(), combat.getKey());
            combat.onAttack(attacker, event.getEntity(), level, attacker.getMainHandItem(), event.getOriginalDamage());
        });
    }

    @SubscribeEvent
    public static void onHurt(LivingIncomingDamageEvent event) {
        if(!(event.getSource().getEntity() instanceof  Player attacker)) return;

        ModEnchantmentRegistry.getAllCombat().forEach(combat -> {
            int level = EnchantmentUtils.getLevel(attacker.getMainHandItem(), combat.getKey());
            combat.onHurt(attacker, event.getEntity(), level, event.getAmount());
        });
    }


//    @SubscribeEvent
//    public void onPlayerHurt(LivingDamageEvent.Post event) {
//        if(!(event.getEntity() instanceof ServerPlayer player)) return;
//
//        ItemStack chestplate = player.getItemBySlot(EquipmentSlot.CHEST);
//        int paybackLevel = EnchantmentUtils.getLevel(chestplate, "easyitems:payback");
//        if(paybackLevel <= 0) return;
//
//        int duration = 2 * paybackLevel;
//        PaybackTracker.activate(player, duration);
//    }


    @SubscribeEvent
    public static void onDamagePost(LivingDamageEvent.Post event) {

        if(!(event.getSource().getEntity() instanceof Player attacker)) return;

        ModEnchantmentRegistry.getAllCombat().forEach(combat -> {
           int level = EnchantmentUtils.getLevel(attacker.getMainHandItem(), combat.getKey());
           combat.postAttack(attacker, event.getEntity(), level, attacker.getMainHandItem());
        });
    }
}
