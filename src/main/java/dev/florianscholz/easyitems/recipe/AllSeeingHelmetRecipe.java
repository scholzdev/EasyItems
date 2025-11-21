package dev.florianscholz.easyitems.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import java.util.*;

//public record AllSeeingHelmetRecipe(List<String> pattern, Map<Character, Ingredient> key, ItemStack output) implements CraftingRecipe {
//
//    @Override
//    public NonNullList<Ingredient> getIngredients() {
//        Set<Ingredient> uniqueIngredients = new HashSet<>(key.values());
//        return NonNullList.of(Ingredient.EMPTY, uniqueIngredients.toArray(new Ingredient[0]));
//    }
//
//    @Override
//    public CraftingBookCategory category() {
//        return CraftingBookCategory.MISC;
//    }
//
//    @Override
//    public boolean canCraftInDimensions(int width, int height) {
//        return width >= 3 && height >= 3;
//    }
//
//    @Override
//    public ItemStack getResultItem(HolderLookup.Provider registries) {
//        return output;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return ModRecipes.ALL_SEEING_HELMET_SERIALIZER.get();
//    }
//
//    @Override
//    public RecipeType<?> getType() {
//        return ModRecipes.ALL_SEEING_HELMET_TYPE.get();
//    }
//
//    public static class Serializer implements RecipeSerializer<AllSeeingHelmetRecipe> {
//
//        public static final Codec<ItemStack> ITEMSTACK_CODEC = RecordCodecBuilder.create(inst -> inst.group(
//                net.minecraft.core.registries.BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(ItemStack::getItem),
//                Codec.INT.optionalFieldOf("count", 1).forGetter(ItemStack::getCount)
//        ).apply(inst, ItemStack::new));
//
//        public static final MapCodec<AllSeeingHelmetRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
//                Codec.STRING.listOf().fieldOf("pattern").forGetter(AllSeeingHelmetRecipe::pattern),
//                Codec.unboundedMap(Codec.STRING.xmap(s -> s.charAt(0), c -> c.toString()), Ingredient.CODEC_NONEMPTY).fieldOf("key").forGetter(AllSeeingHelmetRecipe::key),
//                ITEMSTACK_CODEC.fieldOf("result").forGetter(AllSeeingHelmetRecipe::output)
//        ).apply(inst, AllSeeingHelmetRecipe::new));
//
//        public static final StreamCodec<RegistryFriendlyByteBuf, Map<Character, Ingredient>> CHAR_INGREDIENT_MAP_CODEC =
//                StreamCodec.of(
//                        (buf, map) -> {
//                            buf.writeVarInt(map.size());
//                            for (Map.Entry<Character, Ingredient> entry : map.entrySet()) {
//                                buf.writeUtf(entry.getKey().toString());
//                                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, entry.getValue());
//                            }
//                        },
//                        buf -> {
//                            int size = buf.readVarInt();
//                            Map<Character, Ingredient> map = new HashMap<>();
//                            for (int i = 0; i < size; i++) {
//                                char c = buf.readUtf().charAt(0);
//                                Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
//                                map.put(c, ingredient);
//                            }
//                            return map;
//                        }
//                );
//
//
//        public static final StreamCodec<RegistryFriendlyByteBuf, TungstenInfuserRecipe> STREAM_CODEC =
//                StreamCodec.composite(
//                        Ingredient.CONTENTS_STREAM_CODEC, TungstenInfuserRecipe::inputItem,
//                        ItemStack.STREAM_CODEC, TungstenInfuserRecipe::output,
//                        TungstenInfuserRecipe::new);
//
//        @Override
//        public MapCodec<AllSeeingHelmetRecipe> codec() {
//            return CODEC;
//        }
//
//        @Override
//        public StreamCodec<RegistryFriendlyByteBuf, AllSeeingHelmetRecipe> streamCodec() {
//            return null;
//        }
//    }
//}