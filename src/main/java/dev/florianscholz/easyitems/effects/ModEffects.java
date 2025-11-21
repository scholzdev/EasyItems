package dev.florianscholz.easyitems.effects;

import dev.florianscholz.easyitems.EasyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, EasyItems.MOD_ID);
    public static final DeferredHolder<MobEffect, MobEffect> VISUAL_WITHER = MOB_EFFECTS.register("visual_wither", VisualWither::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

}
