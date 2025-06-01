package dev.florianscholz.easyitems.item;

import dev.florianscholz.easyitems.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    public static Tier IRON = new SimpleTier(ModTags.Blocks.NEEDS_PICKAXE_TOOL,850, 3f, 3f, 22, () -> Ingredient.of(ModItems.CRAFTABLE_IRON_PICKAXE));
    public static Tier GOLD = new SimpleTier(ModTags.Blocks.NEEDS_PICKAXE_TOOL,100, 2f, 2f, 12, () -> Ingredient.of(ModItems.CRAFTABLE_GOLDEN_PICKAXE));
    public static Tier DIAMOND = new SimpleTier(ModTags.Blocks.NEEDS_PICKAXE_TOOL,2000, 4f, 3f, 28, () -> Ingredient.of(ModItems.CRAFTABLE_DIAMOND_PICKAXE));

}
