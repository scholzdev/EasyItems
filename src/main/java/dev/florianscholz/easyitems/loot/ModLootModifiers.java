package dev.florianscholz.easyitems.loot;

import com.mojang.serialization.MapCodec;
import dev.florianscholz.easyitems.EasyItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, EasyItems.MOD_ID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ENCHANTED_BOOK_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_enchanted_book_item", () -> AddEnchantedBookItemModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_RANDOM_ENCHANTED_BOOK_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_random_enchanted_book_item", () -> AddRandomEnchantedBookItemModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> REAPER_LOOT_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("reaper_loot_modifier", () -> ReaperLootModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}