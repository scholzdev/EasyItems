package dev.florianscholz.easyitems.food;

import dev.florianscholz.easyitems.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties SOUL = new FoodProperties.Builder().nutrition(20)
            .effect(() -> new MobEffectInstance(ModEffects.VISUAL_WITHER, 6000), 1)
            .alwaysEdible()
            .build();
}