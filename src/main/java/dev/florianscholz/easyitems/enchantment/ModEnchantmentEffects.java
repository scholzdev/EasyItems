package dev.florianscholz.easyitems.enchantment;

import com.mojang.serialization.MapCodec;
import dev.florianscholz.easyitems.EasyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENTS_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, EasyItems.MOD_ID);
    public static final DeferredRegister<MapCodec<? extends EnchantmentValueEffect>> VALUE_ENCHANTMENTS_EFFECTS = DeferredRegister.create(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE, EasyItems.MOD_ID);

//    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> CLIMBING = ENTITY_ENCHANTMENTS_EFFECTS.register("climbing", () -> ClimbingEnchantmentEffect.CODEC);

//    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BLEEDING = ENTITY_ENCHANTMENTS_EFFECTS.register("bleeding", () -> Ignite.CODEC);

//    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIFESTEAL = ENTITY_ENCHANTMENTS_EFFECTS.register("lifesteal", () -> LifestealEnchantmentEffect.CODEC);

//    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> FREEZE = ENTITY_ENCHANTMENTS_EFFECTS.register("freeze", () -> FreezeEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENTS_EFFECTS.register(eventBus);
        VALUE_ENCHANTMENTS_EFFECTS.register(eventBus);
    }
}
