package dev.florianscholz.easyitems.datagen;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.block.ModBlocks;
import dev.florianscholz.easyitems.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH,
                ModBlocks.BISMUTH_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BISMUTH.get())
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 9)
                .requires(ModBlocks.BISMUTH_BLOCK)
                .unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(recipeOutput);

        oreSmelting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
        oreBlasting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 100, "bismuth");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MATERIAL_INFUSER.get())
                .pattern("TRT")
                .pattern("SFS")
                .pattern("TRT")
                .define('T', ModItems.TUNGSTEN.get())
                .define('R', Items.REDSTONE)
                .define('S', Items.STONE)
                .define('F', Items.FURNACE)
                .unlockedBy("has_tungsten", has(ModItems.TUNGSTEN.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALL_SEEING_EYE.get())
                .pattern("TCT")
                .pattern("RER")
                .pattern("TRT")
                .define('T', ModItems.INFUSED_TUNGSTEN.get())
                .define('C', Items.COMPASS)
                .define('R', Items.REDSTONE)
                .define('E', Items.ENDER_EYE)
                .unlockedBy("has_tungsten", has(ModItems.TUNGSTEN.get()))
                .save(recipeOutput, "all_seeing_eye");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUNGSTEN_HEAD.get())
                .pattern("TTT")
                .pattern("T T")
                .define('T', ModItems.TUNGSTEN.get())
                .unlockedBy("has_tungsten", has(ModItems.TUNGSTEN.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALL_SEEING_EYE.get())
                .pattern(" H ")
                .define('H', ModItems.ALL_SEEING_HELMET.get())
                .unlockedBy("has_helmet", has(ModItems.ALL_SEEING_HELMET.get()))
                .save(recipeOutput, "helmet_to_eye");

        List<ItemLike> TUNGSTEN_SMELTABLES = List.of(ModItems.RAW_TUNGSTEN, ModBlocks.TUNGSTEN_ORE);
        List<ItemLike> ILMENITE_SMELTABLES = List.of(ModItems.RAW_ILMENITE, ModBlocks.ILMENITE_ORE);

        tungstenRecipe(Blocks.DIAMOND_ORE, Items.DIAMOND, recipeOutput);
        tungstenRecipe(Blocks.EMERALD_ORE, Items.EMERALD, recipeOutput);

        // Blueprints

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLUEPRINT_PAPER.get())
                .pattern("PPP")
                .pattern("PL ")
                .define('P', Items.PAPER)
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy("has_lapis", has(Items.LAPIS_LAZULI))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BLUEPRINT_PICKAXE.get())
                .pattern("PPP")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', ModItems.BLUEPRINT_PAPER)
                .define('S', Items.STICK)
                .unlockedBy("has_blueprint_paper", has(ModItems.BLUEPRINT_PAPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALL_SEEING_HELMET.get())
                .pattern(" E ")
                .pattern(" H ")
                .pattern("   ")
                .define('E', ModItems.ALL_SEEING_EYE)
                .define('H', ModItems.TUNGSTEN_HEAD)
                .unlockedBy("has_helmet", has(ModItems.TUNGSTEN_HEAD))
                .unlockedBy("has_eye", has(ModItems.ALL_SEEING_EYE))
                .save(recipeOutput);

        craftablePickaxeRecipe(ModItems.CRAFTABLE_IRON_PICKAXE, Items.IRON_PICKAXE, recipeOutput);
        craftablePickaxeRecipe(ModItems.CRAFTABLE_GOLDEN_PICKAXE, Items.GOLDEN_PICKAXE, recipeOutput);
        craftablePickaxeRecipe(ModItems.CRAFTABLE_DIAMOND_PICKAXE, Items.DIAMOND_PICKAXE, recipeOutput);

        pickaxeHeaderRecipe(ModItems.PICKAXE_HEAD_IRON, Items.IRON_INGOT, recipeOutput);
        pickaxeHeaderRecipe(ModItems.PICKAXE_HEAD_GOLD, Items.GOLD_INGOT, recipeOutput);
        pickaxeHeaderRecipe(ModItems.PICKAXE_HEAD_DIAMOND, Items.DIAMOND, recipeOutput);
//        pickaxeHeaderRecipe(ModItems.PICKAXE_HEAD_NETHERITE, Items.NETHERITE_INGOT, recipeOutput);

//        pickaxeFromHeadRecipe(ModItems.CRAFTABLE_IRON_PICKAXE, ModItems.PICKAXE_HEAD_IRON, recipeOutput);
//        pickaxeFromHeadRecipe(ModItems.CRAFTABLE_GOLDEN_PICKAXE, ModItems.PICKAXE_HEAD_GOLD, recipeOutput);
//        pickaxeFromHeadRecipe(ModItems.CRAFTABLE_DIAMOND_PICKAXE, ModItems.PICKAXE_HEAD_DIAMOND, recipeOutput);
//        pickaxeFromHeadRecipe(Items.NETHERITE_PICKAXE, ModItems.PICKAXE_HEAD_NETHERITE, recipeOutput);

        oreSmelting(recipeOutput, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN.get(), 0.25f, 200, "tungsten");
        oreBlasting(recipeOutput, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN.get(), 0.25f, 100, "tungsten");

        oreSmelting(recipeOutput, ILMENITE_SMELTABLES, RecipeCategory.MISC, ModItems.ILMENITE.get(), 0.25f, 200, "ilmite");
        oreBlasting(recipeOutput, ILMENITE_SMELTABLES, RecipeCategory.MISC, ModItems.ILMENITE.get(), 0.25f, 100, "ilmite");
    }

    private void craftablePickaxeRecipe(ItemLike result, Item item, RecipeOutput recipeOutput) {
       ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
               .requires(item)
               .unlockedBy("has_item", has(item))
               .save(recipeOutput);
    }

    private void pickaxeFromHeadRecipe(ItemLike result, ItemLike head, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("MB ")
                .define('M', ItemTags.PICKAXES)
                .define('B', head)
                .unlockedBy("has_blueprint_paper", has(ModItems.BLUEPRINT_PAPER))
                .save(recipeOutput);
    }

    private void pickaxeHeaderRecipe(ItemLike result, Item item, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("MMM")
                .pattern(" B ")
                .define('M', item)
                .define('B', ModItems.BLUEPRINT_PICKAXE)
                .unlockedBy("has_blueprint_paper", has(ModItems.BLUEPRINT_PAPER))
                .save(recipeOutput);
    }

    private void tungstenRecipe(ItemLike result, Item item, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern("STS")
                .pattern("TIT")
                .pattern("STS")
                .define('S', Items.STONE)
                .define('T', ModItems.INFUSED_TUNGSTEN)
                .define('I', item)
                .unlockedBy("has_infused_tungsten", has(ModItems.INFUSED_TUNGSTEN))
                .save(recipeOutput);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, EasyItems.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}