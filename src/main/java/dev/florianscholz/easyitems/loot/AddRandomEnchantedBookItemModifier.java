package dev.florianscholz.easyitems.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

import java.util.Comparator;
import java.util.List;

public class AddRandomEnchantedBookItemModifier extends LootModifier {

    public record LevelChance(int level, float chance) {
        public static final Codec<LevelChance> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                   Codec.INT.fieldOf("level").forGetter(LevelChance::level),
                   Codec.FLOAT.fieldOf("chance").forGetter(LevelChance::chance)
                ).apply(instance, LevelChance::new)
        );
    }

    public static final MapCodec<AddRandomEnchantedBookItemModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
        codecStart(instance)
                .and(ResourceLocation.CODEC.fieldOf("enchantment").forGetter(m -> m.enchantmentId))
                .and(LevelChance.CODEC.listOf().fieldOf("levels").forGetter(m -> m.levelChances))
                .apply(instance, AddRandomEnchantedBookItemModifier::new)
    );

    private final ResourceLocation enchantmentId;
    private final List<LevelChance> levelChances;

    public AddRandomEnchantedBookItemModifier(LootItemCondition[] conditions, ResourceLocation enchantmentId, List<LevelChance> levelChances) {
        super(conditions);
        this.enchantmentId = enchantmentId;

        this.levelChances = levelChances.stream()
                .sorted(Comparator.comparingInt(LevelChance::level).reversed())
                .toList();
    }

    private int getRolledLevel(RandomSource random) {
        for(LevelChance chance : levelChances) {
            if(random.nextFloat() < chance.chance()) {
                return chance.level();
            }
        }
        return 0;
    };

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        if(context.getLevel().dimension() != Level.NETHER) return generatedLoot;

        int level = getRolledLevel(context.getRandom());

        if(level == 0) return generatedLoot;

        var registries = context.getLevel().registryAccess();
        var enchantmentKey = ResourceKey.create(Registries.ENCHANTMENT, enchantmentId);
        var enchantmentHolder = registries.lookupOrThrow(Registries.ENCHANTMENT)
                .get(enchantmentKey)
                .orElseThrow();

        ItemStack stack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantmentHolder, level));

        generatedLoot.add(stack);
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
