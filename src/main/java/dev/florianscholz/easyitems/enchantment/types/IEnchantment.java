package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public interface IEnchantment {

    ResourceKey<Enchantment> getKey();

    TagKey<Item> getSupportedItems();

    default int getWeight() {
        return 1;
    }

    default int getMaxLevel() {
        return 1;
    }

    default int getMinCostBase() {
        return 5;
    }

    default int getMinCostPerLevel() {
        return 8;
    }

    default int getMaxCostBase() {
        return 20;
    }

    default int getMaxCostPerLevel() {
        return 8;
    }

    default int getAnvilCost() {
        return 2;
    }

    EquipmentSlot getSlot();
    EquipmentSlotGroup getSlotGroup();
    TagKey<Enchantment> getExclusiveWith();

    default String getName() {
        return this.getKey().location().getPath();
    }
}