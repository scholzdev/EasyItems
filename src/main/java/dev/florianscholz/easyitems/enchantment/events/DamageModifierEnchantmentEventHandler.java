package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class DamageModifierEnchantmentEventHandler {

        @SubscribeEvent
        public static void onDamagePre(LivingDamageEvent.Pre event) {

            if(!(event.getSource().getEntity() instanceof  Player attacker)) return;

            ModEnchantmentRegistry.getAllDamageModifiers().forEach(modifier -> {
                int level = EnchantmentUtils.getLevel(attacker.getMainHandItem(), modifier.getKey());

                if(level <= 0) return;

                float multiplier = modifier.modifyOutgoingDamage(attacker, event.getEntity(), event.getOriginalDamage(), level, attacker.getMainHandItem());
                event.setNewDamage(event.getOriginalDamage() * multiplier);
            });
        }
}
