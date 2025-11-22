package dev.florianscholz.easyitems.datagen;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.recipe.SpawnEggRecipe;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registers) {
        super(output, registers);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        SpecialRecipeBuilder.special(SpawnEggRecipe::new)
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "spawn_egg_from_head"));
    }
}
