package dev.florianscholz.easyitems.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Optional;

public class AddEnchantedBookItemModifier extends LootModifier {

    public static final MapCodec<AddEnchantedBookItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance)
                    .and(ResourceLocation.CODEC.fieldOf("itemId").forGetter(m -> m.itemId))
                    .and(ResourceLocation.CODEC.optionalFieldOf("enchantmentId").forGetter(m -> m.enchantmentId))
                    .and(com.mojang.serialization.Codec.INT.optionalFieldOf("level", 1).forGetter(m -> m.level))
                    .apply(instance, AddEnchantedBookItemModifier::new)
    );

    private final ResourceLocation itemId;
    private final Optional<ResourceLocation> enchantmentId;
    private final int level;

    public AddEnchantedBookItemModifier(LootItemCondition[] conditions, ResourceLocation itemId, Optional<ResourceLocation> enchantmentId, int level) {
        super(conditions);
        this.itemId = itemId;
        this.enchantmentId = enchantmentId;
        this.level = level;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        var registries = context.getLevel().registryAccess();

        // Convert ResourceLocation to ResourceKey and resolve the Item
        var itemKey = ResourceKey.create(Registries.ITEM, itemId);
        var item = registries.lookupOrThrow(Registries.ITEM)
                .get(itemKey)
                .orElseThrow()
                .value();

        ItemStack stack;

        // Resolve the enchantment (if provided)
        if (enchantmentId.isPresent()) {
            var enchKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentId.get());
            var enchHolder = registries.lookupOrThrow(Registries.ENCHANTMENT)
                    .get(enchKey)
                    .orElseThrow();

            stack = EnchantedBookItem.createForEnchantment(
                    new EnchantmentInstance(enchHolder, this.level)
            );
        } else {
            stack = new ItemStack(item);
        }

        generatedLoot.add(stack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}