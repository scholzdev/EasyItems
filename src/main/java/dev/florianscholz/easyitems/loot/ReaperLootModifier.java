package dev.florianscholz.easyitems.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReaperLootModifier extends LootModifier {

    public static final MapCodec<ReaperLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance).and(
                    instance.group(
                            ExtraCodecs.POSITIVE_FLOAT.fieldOf("soul_chance").forGetter(m -> (float) m.soulChance),
                            ExtraCodecs.POSITIVE_FLOAT.fieldOf("head_chance").forGetter(m -> (float) m.headChance),
                            ItemStack.CODEC.fieldOf("soul").forGetter(m -> m.soul),
                            ItemStack.CODEC.fieldOf("head").forGetter(m -> m.head)
                    )
            ).apply(instance, ReaperLootModifier::new)
    );

    private final ItemStack head;
    private final ItemStack soul;
    private final double headChance;
    private final double soulChance;

    public ReaperLootModifier(LootItemCondition[] conditionsIn, double soulChance, double headChance, ItemStack soul, @Nullable ItemStack head) {
        super(conditionsIn);
        this.head = head;
        this.soul = soul;
        this.headChance = headChance;
        this.soulChance = soulChance;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Entity killerEntity = context.getParamOrNull(LootContextParams.ATTACKING_ENTITY);

        if (!(killerEntity instanceof LivingEntity killer)) {
            return generatedLoot;
        }

        ItemStack weapon = killer.getMainHandItem();

        if (weapon.isEmpty()) {
            return generatedLoot;
        }

        var enchantmentRegistry = context.getLevel().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        var reaperEnchantment = enchantmentRegistry.get(ModEnchantmentKeys.REAPER);

        if (reaperEnchantment == null) {
            return generatedLoot;
        }

        int level = EnchantmentUtils.getLevel(weapon, ModEnchantmentKeys.REAPER);

        if (level <= 0) {
            return generatedLoot;
        }

        double actualSoulChance = soulChance + (level * 0.10);
        if (context.getRandom().nextDouble() < actualSoulChance) {
            generatedLoot.add(soul.copy());
        }

        if(head == null) return generatedLoot;

        double actualHeadChance = headChance + (level * 0.05);
        if (context.getRandom().nextDouble() < actualHeadChance) {
            generatedLoot.add(head.copy());
        }

        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}