package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.DamageModifierEnchantment;
import dev.florianscholz.easyitems.enchantment.types.InputModifierEnchantment;
import dev.florianscholz.easyitems.enchantment.types.TickableEnchantment;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.UUID;

public class DoubleJumpEnchantment extends BaseEnchantment implements TickableEnchantment, InputModifierEnchantment {

    public DoubleJumpEnchantment() {
        super(
                ModEnchantmentKeys.DOUBLE_JUMP,
                ItemTags.FOOT_ARMOR_ENCHANTABLE,
                EquipmentSlotGroup.FEET,
                EquipmentSlot.FEET,
                5,
                1,
                5,
                8,
                20,
                8,
                1
        );
    }
    private static final HashMap<UUID, Integer> jumpTracker = new HashMap<>();
    private static final HashMap<UUID, Long> lastJumpTime = new HashMap<>();

    public static void resetJumps(Player player) {
        if (player.onGround()) {
            jumpTracker.put(player.getUUID(), 1);
        }
    }

    public static boolean attemptDoubleJump(Player player, int enchantmentLevel) {
        UUID playerId = player.getUUID();

        if (player.onGround()) {
            return false;
        }

        long currentTime = System.currentTimeMillis();
        Long lastJump = lastJumpTime.get(playerId);
        if (lastJump != null && currentTime - lastJump < 200) {
            return false;
        }

        Integer remainingJumps = jumpTracker.getOrDefault(playerId, 0);

        if (remainingJumps > 0) {
            performDoubleJump(player, enchantmentLevel, remainingJumps);

            jumpTracker.put(playerId, remainingJumps - 1);
            lastJumpTime.put(playerId, currentTime);

            return true;
        }

        return false;
    }

    private static void performDoubleJump(Player player, int enchantmentLevel, int remainingJumps) {
        double jumpStrength = 0.42 + (enchantmentLevel * 0.08);

        Vec3 motion = player.getDeltaMovement();

        double horizontalBoost = 1.1;
        player.setDeltaMovement(
                motion.x * horizontalBoost,
                jumpStrength,
                motion.z * horizontalBoost
        );

        player.fallDistance = Math.max(0, player.fallDistance - 3.0f);
    }

    @Override
    public void onTick(Player player, int level, ItemStack stack, EquipmentSlot slot) {
        resetJumps(player);
    }

    @Override
    public void onInput(Player player, int key, int action) {
        if (key == GLFW.GLFW_KEY_SPACE && action == GLFW.GLFW_PRESS) {
            attemptDoubleJump(player, 1);
        }
    }
}

