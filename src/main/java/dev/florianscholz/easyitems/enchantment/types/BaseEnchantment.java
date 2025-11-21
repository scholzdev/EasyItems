package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;

public abstract class BaseEnchantment implements IEnchantment {

    private final ResourceKey<Enchantment> key;
    private final TagKey<Item> supportedItems;
    private final EquipmentSlotGroup slotGroup;
    private final EquipmentSlot slot;

    private final int weight;
    private final int maxLevel;
    private final int minCostBase;
    private final int minCostPerLevel;
    private final int maxCostBase;
    private final int maxCostPerLevel;
    private final int anvilCost;

    @Nullable
    private final TagKey<Enchantment> exclusiveSet;

    // Constructor MIT Exclusive Set
    protected BaseEnchantment(
            ResourceKey<Enchantment> key,
            TagKey<Item> supportedItems,
            @Nullable TagKey<Enchantment> exclusiveSet,
            EquipmentSlotGroup slotGroup,
            EquipmentSlot slot,
            int weight,
            int maxLevel,
            int minCostBase,
            int minCostPerLevel,
            int maxCostBase,
            int maxCostPerLevel,
            int anvilCost
    ) {
        this.key = key;
        this.supportedItems = supportedItems;
        this.exclusiveSet = exclusiveSet;
        this.slotGroup = slotGroup;
        this.slot = slot;
        this.weight = weight;
        this.maxLevel = maxLevel;
        this.minCostBase = minCostBase;
        this.minCostPerLevel = minCostPerLevel;
        this.maxCostBase = maxCostBase;
        this.maxCostPerLevel = maxCostPerLevel;
        this.anvilCost = anvilCost;
    }

    protected BaseEnchantment(
            ResourceKey<Enchantment> key,
            TagKey<Item> supportedItems,
            EquipmentSlotGroup slotGroup,
            EquipmentSlot slot,
            int weight,
            int maxLevel,
            int minCostBase,
            int minCostPerLevel,
            int maxCostBase,
            int maxCostPerLevel,
            int anvilCost
    ) {
        this(key, supportedItems, null, slotGroup, slot, weight, maxLevel, minCostBase, minCostPerLevel, maxCostBase, maxCostPerLevel, anvilCost);
    }

    @Override
    public ResourceKey<Enchantment> getKey() {
        return key;
    }

    @Override
    public TagKey<Item> getSupportedItems() {
        return supportedItems;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getMinCostBase() {
        return minCostBase;
    }

    @Override
    public int getMinCostPerLevel() {
        return minCostPerLevel;
    }

    @Override
    public int getMaxCostBase() {
        return maxCostBase;
    }

    @Override
    public int getMaxCostPerLevel() {
        return maxCostPerLevel;
    }

    @Override
    public int getAnvilCost() {
        return anvilCost;
    }

    @Override
    public EquipmentSlotGroup getSlotGroup() {
        return slotGroup;
    }

    @Override
    public EquipmentSlot getSlot() {
        return slot;
    }

    @Nullable
    public TagKey<Enchantment> getExclusiveWith() {
        return exclusiveSet;
    }
}