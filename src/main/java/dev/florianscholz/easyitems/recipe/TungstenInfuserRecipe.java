package dev.florianscholz.easyitems.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record TungstenInfuserRecipe(Ingredient inputItem, ItemStack output) implements Recipe<TungstenInfuserRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(TungstenInfuserRecipeInput tungstenInfuserRecipeInput, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return inputItem.test(tungstenInfuserRecipeInput.getItem(0));
    }

    @Override
    public ItemStack assemble(TungstenInfuserRecipeInput tungstenInfuserRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.TUNGSTEN_INFUSER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.TUNGSTEN_INFUSER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<TungstenInfuserRecipe> {
        public static final MapCodec<TungstenInfuserRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(TungstenInfuserRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(TungstenInfuserRecipe::output)
        ).apply(inst, TungstenInfuserRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TungstenInfuserRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, TungstenInfuserRecipe::inputItem,
                        ItemStack.STREAM_CODEC, TungstenInfuserRecipe::output,
                        TungstenInfuserRecipe::new);

        @Override
        public MapCodec<TungstenInfuserRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TungstenInfuserRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}