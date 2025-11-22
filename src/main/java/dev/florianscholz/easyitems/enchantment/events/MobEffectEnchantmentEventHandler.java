package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class MobEffectEnchantmentEventHandler {

    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
        if(!(event.getSource().getEntity() instanceof Player attacker)) return;
        LivingEntity target = event.getEntity();

        ItemStack weapon = attacker.getMainHandItem();
        float damage = event.getOriginalDamage();

        ModEnchantmentRegistry.getAllMobEffects().forEach(effect -> {
            int level = EnchantmentUtils.getLevel(weapon, effect.getKey());
            if(level <= 0) return;

            effect.onEffectAttack(attacker, target, level, weapon, damage);
        });
    }

    @SubscribeEvent
    public static void onPotionApplicable(MobEffectEvent.Applicable event) {
        if(!(event.getEntity() instanceof Player player)) return;

        MobEffectInstance effectInstance = event.getEffectInstance();

        ModEnchantmentRegistry.getAllMobEffects().forEach(effect -> {

            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            int level = EnchantmentUtils.getLevel(chest, effect.getKey());
            if(level <= 0) return;

            List<Holder<MobEffect>> handeledEffects = effect.getHandledEffects();
            if(handeledEffects != null && !handeledEffects.contains(effectInstance.getEffect())) return;

            boolean shouldBlock = effect.onEffectImmunityCheck(player, effectInstance);
            if(shouldBlock)
                event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);

        });
    }

    @SubscribeEvent
    public static void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        ModEnchantmentRegistry.getAllMobEffects().forEach(enchantment -> {
            List<Holder<MobEffect>> handeledEffects = enchantment.getHandledEffects();
            if(handeledEffects == null) return;

            player.getActiveEffects().forEach(effect -> {
                if(handeledEffects.contains(effect.getEffect())) {
                    enchantment.onEffectTick(player, effect);
                }
            });

        });
    }
}
