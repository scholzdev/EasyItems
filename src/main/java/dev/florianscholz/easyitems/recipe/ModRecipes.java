package dev.florianscholz.easyitems.recipe;

import dev.florianscholz.easyitems.EasyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, EasyItems.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, EasyItems.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<TungstenInfuserRecipe>> TUNGSTEN_INFUSER_SERIALIZER =
            SERIALIZERS.register("tungsten_infuser", TungstenInfuserRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<TungstenInfuserRecipe>> TUNGSTEN_INFUSER_TYPE =
            TYPES.register("tungsten_infuser", () -> new RecipeType<TungstenInfuserRecipe>() {
                @Override
                public String toString() {
                    return "tungsten_infuser";
                }
            });

//    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AllSeeingHelmetRecipe>> ALL_SEEING_HELMET_SERIALIZER =
//            SERIALIZERS.register("all_seeing_helmet", AllSeeingHelmetRecipe.Serializer::new);
//
//    public static final DeferredHolder<RecipeType<?>, RecipeType<AllSeeingHelmetRecipe>> ALL_SEEING_HELMET_TYPE =
//            TYPES.register("all_seeing_helmet", () -> new RecipeType<>() {
//                @Override
//                public String toString() {
//                    return "all_seeing_helmet";
//                }
//            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}