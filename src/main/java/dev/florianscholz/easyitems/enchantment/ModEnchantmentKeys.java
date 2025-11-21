package dev.florianscholz.easyitems.enchantment;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.types.IEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.*;

import java.util.List;

public class ModEnchantmentKeys {

    private static ResourceKey<Enchantment> key(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, name));
    }

    public static final ResourceKey<Enchantment> PHOTOSYNTHESIS = key("photosynthesis");
    public static final ResourceKey<Enchantment> NOCTURNAL = key("nocturnal");
    public static final ResourceKey<Enchantment> BERSERK = key("berserk");
    public static final ResourceKey<Enchantment> PAYBACK = key("payback");
    public static final ResourceKey<Enchantment> SMELTING = key("smelting");
    public static final ResourceKey<Enchantment> DOUBLE_JUMP = key("double_jump");
    public static final ResourceKey<Enchantment> ENLIGHTENMENT = key("enlightenment");
    public static final ResourceKey<Enchantment> REAPER = key("reaper");
    public static final ResourceKey<Enchantment> GOURMET = key("gourmet");

    public static void bootstrap(BootstrapContext<Enchantment> context) {

        var enchantments = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, GOURMET, Enchantment.enchantment(Enchantment.definition(
                items.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                items.getOrThrow(ItemTags.CHEST_ARMOR),
                5,
                2,
                Enchantment.dynamicCost(5, 7),
                Enchantment.dynamicCost(25, 7),
                2,
                EquipmentSlotGroup.CHEST))
        );

//        ModEnchantmentRegistry.getAllEnchantments().forEach(enchantment -> {
//            ModEnchantmentRegistry.register(context, enchantment);
//        });
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }

    public static List<ResourceKey<Enchantment>> getAllKeys() {
        return List.of(PHOTOSYNTHESIS, NOCTURNAL, BERSERK, PAYBACK, SMELTING, DOUBLE_JUMP, ENLIGHTENMENT, REAPER, GOURMET);
    }
}
