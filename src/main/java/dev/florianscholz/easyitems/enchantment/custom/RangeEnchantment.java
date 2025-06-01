package dev.florianscholz.easyitems.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public class RangeEnchantment implements EnchantmentValueEffect {
    public static final MapCodec<RangeEnchantment> CODEC = MapCodec.unit(RangeEnchantment::new);

    @Override
    public float process(int enchantmentLevel, RandomSource random, float value) {
        return 0;
    }

    @Override
    public MapCodec<? extends EnchantmentValueEffect> codec() {
        return CODEC;
    }
}
