package dev.florianscholz.easyitems.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ClimbingEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<ClimbingEnchantmentEffect> CODEC = MapCodec.unit(ClimbingEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if(!(item.owner() instanceof Player)) return;

        if(entity.horizontalCollision) {
            Vec3 initialVec = entity.getDeltaMovement();
            Vec3 climbVec = new Vec3(initialVec.x(), 0.2D, initialVec.z());
            entity.setDeltaMovement(climbVec.scale(0.96D));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
