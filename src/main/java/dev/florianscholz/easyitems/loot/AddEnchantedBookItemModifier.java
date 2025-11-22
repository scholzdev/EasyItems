package dev.florianscholz.easyitems.loot;

import com.mojang.serialization.Codec;
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
                    .and(ResourceLocation.CODEC.fieldOf("enchantment").forGetter(m -> m.enchantmentId))
                    .and(Codec.INT.fieldOf("level").forGetter(m -> m.level))
                    .apply(instance, AddEnchantedBookItemModifier::new)
    );

    private final ResourceLocation enchantmentId;
    private final int level;

    public AddEnchantedBookItemModifier(LootItemCondition[] conditions, ResourceLocation enchantmentId, int level) {
        super(conditions);
        this.enchantmentId = enchantmentId;
        this.level = level;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        var registries = context.getLevel().registryAccess();

        var enchKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentId);
        var enchHolder = registries.lookupOrThrow(Registries.ENCHANTMENT)
                .get(enchKey)
                .orElseThrow();

        ItemStack stack = EnchantedBookItem.createForEnchantment(
                new EnchantmentInstance(enchHolder, this.level)
        );

        generatedLoot.add(stack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}