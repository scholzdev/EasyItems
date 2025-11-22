package dev.florianscholz.easyitems.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.florianscholz.easyitems.component.ModDataComponents;
import dev.florianscholz.easyitems.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import java.util.Map;

public class SpawnEggRecipe extends CustomRecipe {

    private static final Map<Item, String> HEAD_TO_ENTITY = Map.of(
            Items.ZOMBIE_HEAD, "zombie",
            Items.SKELETON_SKULL, "skeleton",
            Items.CREEPER_HEAD, "creeper",
            Items.PIGLIN_HEAD, "piglin",
            Items.DRAGON_HEAD, "ender_dragon",
            Items.WITHER_SKELETON_SKULL, "wither_skeleton"
    );

    private static final Map<String, Item> ENTITY_TO_EGG = Map.of(
            "zombie", Items.ZOMBIE_SPAWN_EGG,
            "skeleton", Items.SKELETON_SPAWN_EGG,
            "creeper", Items.CREEPER_SPAWN_EGG,
            "piglin", Items.PIGLIN_SPAWN_EGG,
            "ender_dragon", Items.ENDER_DRAGON_SPAWN_EGG,
            "wither_skeleton", Items.WITHER_SKELETON_SPAWN_EGG
    );

    public SpawnEggRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.width() != 1 || input.height() != 3) {
            return false;
        }

        ItemStack top = input.getItem(0);
        ItemStack middle = input.getItem(1);
        ItemStack bottom = input.getItem(2);

        String headEntity = HEAD_TO_ENTITY.get(top.getItem());
        if (headEntity == null) {
            return false;
        }

        if (!middle.is(ModItems.SOUL.get())) {
            return false;
        }

        String soulEntity = middle.get(ModDataComponents.SOUL_ENTITY_TYPE.get());
        if (soulEntity == null || !soulEntity.equals(headEntity)) {
            return false;
        }

        return bottom.is(Items.EGG);
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack head = input.getItem(0);
        String entityName = HEAD_TO_ENTITY.get(head.getItem());

        if(entityName == null) return ItemStack.EMPTY;

        Item eggItem = ENTITY_TO_EGG.get(entityName);
        if (eggItem == null) return ItemStack.EMPTY;

        return new ItemStack(eggItem);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width == 1 && height == 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipe.SPAWN_EGG_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<SpawnEggRecipe> {
        public static final MapCodec<SpawnEggRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                CraftingBookCategory.CODEC.fieldOf("category")
                        .orElse(CraftingBookCategory.MISC)
                        .forGetter(r -> r.category())
            ).apply(instance, SpawnEggRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, SpawnEggRecipe> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.fromCodec(CraftingBookCategory.CODEC),
                SpawnEggRecipe::category,
                SpawnEggRecipe::new
        );

        @Override
        public MapCodec<SpawnEggRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SpawnEggRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    };

}
