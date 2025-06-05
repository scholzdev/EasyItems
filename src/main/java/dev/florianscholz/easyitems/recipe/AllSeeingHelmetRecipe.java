package dev.florianscholz.easyitems.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.florianscholz.easyitems.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public record AllSeeingHelmetRecipe(List<Ingredient> ingredients, ItemStack output) implements CraftingRecipe {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.addAll(ingredients);
        return list;
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.MISC;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (level.isClientSide) return false;

        boolean foundHead = false;
        boolean foundEye = false;

        for (int i = 0; i < input.size(); i++) {
            ItemStack item = input.getItem(i);
            if (item.isEmpty()) continue;

            if (item.is(ModItems.ALL_SEEING_TUNGSTEN_HEAD.get())) {
                if (foundHead) return false; // duplicate
                foundHead = true;
            } else if (item.is(ModItems.ALL_SEEING_EYE.get())) {
                if (foundEye) return false; // duplicate
                foundEye = true;
            } else {
                return false; // invalid item
            }
        }

        return foundHead && foundEye;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.ALL_SEEING_HELMET_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALL_SEEING_HELMET_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<AllSeeingHelmetRecipe> {

        public static final MapCodec<AllSeeingHelmetRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredient").forGetter(AllSeeingHelmetRecipe::ingredients),
                ItemStack.CODEC.fieldOf("result").forGetter(AllSeeingHelmetRecipe::output)
        ).apply(inst, AllSeeingHelmetRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, List<Ingredient>> INGREDIENT_LIST_STREAM_CODEC =
                StreamCodec.of(
                        (buf, list) -> {
                            buf.writeVarInt(list.size());
                            for (Ingredient ingredient : list) {
                                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
                            }
                        },
                        buf -> {
                            int size = buf.readVarInt();
                            List<Ingredient> list = new java.util.ArrayList<>(size);
                            for (int i = 0; i < size; i++) {
                                list.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            return list;
                        }
                );

        public static final StreamCodec<RegistryFriendlyByteBuf, AllSeeingHelmetRecipe> STREAM_CODEC =
                StreamCodec.composite(INGREDIENT_LIST_STREAM_CODEC, AllSeeingHelmetRecipe::ingredients,
                        ItemStack.STREAM_CODEC, AllSeeingHelmetRecipe::output,
                        AllSeeingHelmetRecipe::new
                );

        @Override
        public MapCodec<AllSeeingHelmetRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, AllSeeingHelmetRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
