package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.TickableEnchantment;
import dev.florianscholz.easyitems.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PhotosynthesisEnchantment extends BaseEnchantment implements TickableEnchantment {

    public PhotosynthesisEnchantment() {
        super(
                ModEnchantmentKeys.PHOTOSYNTHESIS,
                ItemTags.HEAD_ARMOR_ENCHANTABLE,
                ModTags.Enchantments.HEALING_EXCLUSIVE,
                EquipmentSlotGroup.HEAD,
                EquipmentSlot.HEAD,
                5,
                3,
                5,
                8,
                20,
                8,
                1
        );
    }

    @Override
    public void onTick(Player player, int level, ItemStack stack, EquipmentSlot slot) {
        BlockPos pos = player.blockPosition().above();
        ServerLevel serverLevel = (ServerLevel) player.level();

        if(!serverLevel.canSeeSky(pos) || !serverLevel.isDay()) return;

        int healInterval = calculateHealInterval(level);

        if(player.tickCount % healInterval == 0 && canHeal(player)) {
            heal(player, level);
            spawnParticles(serverLevel, player);
        }
    }

    private int calculateHealInterval(int level) {
        return Math.max(20, 120 - (level * 20));
    }

    private boolean canHeal(Player player) {
        return player.getHealth() < player.getMaxHealth();
    }

    private void heal(Player player, int level) {
        float healAmount = 1.f + (level * 0.5f);
        player.heal(healAmount);
        player.sendSystemMessage(Component.literal("you got healed: " + healAmount));
    }

    private void spawnParticles(ServerLevel level, Player player) {
        if (level.random.nextFloat() < 0.3F) {
            level.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    player.getX() + (level.random.nextDouble() - 0.5D) * 0.5D,
                    player.getY() + level.random.nextDouble() * 2D,
                    player.getZ() + (level.random.nextDouble() - 0.5D) * 0.5D,
                    3,
                    0.2D, 0.2D, 0.2D,
                    0.05D
            );
        }
    }
}