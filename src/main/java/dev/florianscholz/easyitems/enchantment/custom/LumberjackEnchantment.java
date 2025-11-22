package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.TreeInteractionEnchantment;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;

public class LumberjackEnchantment extends BaseEnchantment implements TreeInteractionEnchantment {

    public LumberjackEnchantment() {
        super(
                ModEnchantmentKeys.LUMBERJACK,
                ItemTags.AXES,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlot.MAINHAND,
                5,
                3,
                10,
                10,
                30,
                15,
                2
        );
    }

    @Override
    public float getBonusDropChance(Player player, int level) {
        return 0.1f * level;
    }

    @Override
    public void onTreeFell(Player player, int totalBlocks, int level) {
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    player.getX(), player.getY() + 1, player.getZ(),
                    totalBlocks, 0.5, 0.5, 0.5, 0.1
            );
        }
    }
}