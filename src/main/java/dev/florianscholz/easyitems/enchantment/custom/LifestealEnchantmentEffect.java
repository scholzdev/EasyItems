package dev.florianscholz.easyitems.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record LifestealEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<LifestealEnchantmentEffect> CODEC = MapCodec.unit(LifestealEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {

        if(!(item.owner() instanceof Player)) return;

        Player owner = (Player) item.owner();

        int damage = item.itemStack().getDamageValue();

        float heal = (float) (damage * 0.05 * enchantmentLevel);

        owner.heal(heal);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
