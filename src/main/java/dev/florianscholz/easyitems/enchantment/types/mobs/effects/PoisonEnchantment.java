package dev.florianscholz.easyitems.enchantment.types.mobs.effects;

import dev.florianscholz.easyitems.enchantment.types.mobs.MobEffectEnchantment;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;

import java.util.List;

public interface PoisonEnchantment extends MobEffectEnchantment {

    @Override
    default List<Holder<MobEffect>> getHandledEffects() {
        return List.of(MobEffects.POISON);
    }
}
