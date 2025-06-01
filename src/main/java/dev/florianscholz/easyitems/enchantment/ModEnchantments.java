package dev.florianscholz.easyitems.enchantment;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.datagen.ModEnchantmentTagProvider;
import dev.florianscholz.easyitems.enchantment.custom.ClimbingEnchantmentEffect;
import dev.florianscholz.easyitems.enchantment.custom.LifestealEnchantmentEffect;
import dev.florianscholz.easyitems.util.ModTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import net.minecraft.world.item.enchantment.effects.Ignite;

import java.util.Set;


public class ModEnchantments {

    public static final ResourceKey<Enchantment> CLIMBING = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "climbing"));
    public static final ResourceKey<Enchantment> LIFESTEAL = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "lifesteal"));
    public static final ResourceKey<Enchantment> BLEEDING = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "bleeding"));
    public static final ResourceKey<Enchantment> MINING_RANGE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "mining_range"));
    public static final ResourceKey<Enchantment> ATTACK_RANGE = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "attack_range"));


    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, CLIMBING, Enchantment.enchantment(Enchantment.definition(
            items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
            1,
            1,
            Enchantment.dynamicCost(5, 7),
            Enchantment.dynamicCost(5, 7),
            5,
            EquipmentSlotGroup.FEET))
                .withEffect(EnchantmentEffectComponents.TICK, new ClimbingEnchantmentEffect())
        );

        register(context, LIFESTEAL, Enchantment.enchantment(Enchantment.definition(
            items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
            1,
            5,
            Enchantment.dynamicCost(5, 7),
            Enchantment.dynamicCost(25, 7),
            5,
            EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new LifestealEnchantmentEffect())
        );

        register(context, BLEEDING, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        5,
                        EquipmentSlotGroup.MAINHAND))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new Ignite(LevelBasedValue.constant(2.f)))
        );

        register(context, MINING_RANGE, Enchantment.enchantment(Enchantment.definition(
                        items.getOrThrow(ItemTags.PICKAXES),
                        1,
                        4,
                        Enchantment.dynamicCost(5, 7),
                        Enchantment.dynamicCost(25, 7),
                        5,
                        EquipmentSlotGroup.MAINHAND
                ))
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "mining_range"),
                                Attributes.BLOCK_INTERACTION_RANGE,
                                LevelBasedValue.perLevel(1.F),
                                AttributeModifier.Operation.ADD_VALUE)
                )
        );

        register(context, ATTACK_RANGE, Enchantment.enchantment(Enchantment.definition(
                                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                                1,
                                5,
                                Enchantment.dynamicCost(5, 7),
                                Enchantment.dynamicCost(25, 7),
                                5,
                                EquipmentSlotGroup.MAINHAND
                    ))
                    .withEffect(
                            EnchantmentEffectComponents.ATTRIBUTES,
                            new EnchantmentAttributeEffect(
                                    ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "attack_range"),
                                    Attributes.ENTITY_INTERACTION_RANGE,
                                    LevelBasedValue.perLevel(0.3F),
                                    AttributeModifier.Operation.ADD_VALUE)
                    )
                .exclusiveWith(enchantments.getOrThrow(ModTags.Enchantments.FIRE_ASPECT))
        );

    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

}
