package dev.florianscholz.easyitems.enchantment.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID)
public class DeathEnchantmentEventHandler {

    @SubscribeEvent
    public static void onEntityKilled(LivingDeathEvent event) {

        if(!(event.getSource().getEntity() instanceof Player player)) return;
        if(player.level().isClientSide()) return;

        ModEnchantmentRegistry.getAllDeath().forEach(deathEchantment -> {

            int level = EnchantmentUtils.getLevel(player.getMainHandItem(), deathEchantment.getKey());

            deathEchantment.onEntityKilled(player, event.getEntity(), player.getMainHandItem(), level, event.getEntity().level());
        });


    }

}
