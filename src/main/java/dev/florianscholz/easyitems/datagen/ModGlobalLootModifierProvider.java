package dev.florianscholz.easyitems.datagen;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.item.ModItems;
import dev.florianscholz.easyitems.loot.AddEnchantedBookItemModifier;
import dev.florianscholz.easyitems.loot.AddItemModifier;
import dev.florianscholz.easyitems.loot.ReaperLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    private CompletableFuture<HolderLookup.Provider> registries;
    private HashMap<String, ItemStack> entityStackMap = new HashMap<String, ItemStack>();

    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, EasyItems.MOD_ID);
        this.registries = registries;
        createEntityStackMap();
    }


    @Override
    protected void start() {
        registerHeadAndSoulLootTables();

        this.add("book_from_outpost", new AddEnchantedBookItemModifier(
                new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("chests/pillager_outpost")).build()
                },
                ResourceLocation.withDefaultNamespace("book"),
                Optional.of(ModEnchantmentKeys.GOURMET.location()),
                1
        ));

        this.add("book_from_outpost", new AddEnchantedBookItemModifier(
                new LootItemCondition[]{
                        LootTableIdCondition.builder(ResourceLocation.withDefaultNamespace("chests/pillager_outpost")).build()
                },
                ResourceLocation.withDefaultNamespace("book"),
                Optional.of(ModEnchantmentKeys.PHOTOSYNTHESIS.location()),
                3
        ));

    }
    private void registerHeadAndSoulLootTables() {

        double soulChance = 0.4;
        double headChance = 0.1;

        entityStackMap.entrySet().forEach(set -> {
            String fullPath = set.getKey();
            String mobName = fullPath.substring(fullPath.lastIndexOf("/") + 1);
            ItemStack head = set.getValue();

            this.add("soul_from_" + mobName, new ReaperLootModifier(
                    new LootItemCondition[]{
                            new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace(fullPath)).build()
                    },
                    soulChance,
                    headChance,
                    new ItemStack(ModItems.SOUL.get()),
                    head
            ));
        });

    }

    private void createEntityStackMap() {
        entityStackMap.put("entities/zombie", new ItemStack(Items.ZOMBIE_HEAD));
        entityStackMap.put("entities/skeleton", new ItemStack(Items.SKELETON_SKULL));
        entityStackMap.put("entities/wither_skeleton", new ItemStack(Items.WITHER_SKELETON_SKULL));
        entityStackMap.put("entities/creeper", new ItemStack(Items.CREEPER_HEAD));
        entityStackMap.put("entities/piglin", new ItemStack(Items.PIGLIN_HEAD));
        entityStackMap.put("entities/piglin_brute", new ItemStack(Items.PIGLIN_HEAD));
        entityStackMap.put("entities/player", new ItemStack(Items.PLAYER_HEAD));
        entityStackMap.put("entities/ender_dragon", new ItemStack(Items.DRAGON_HEAD));

    }
}
