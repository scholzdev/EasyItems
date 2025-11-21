package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.BlockEnchantment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class SmeltingEnchantment extends BaseEnchantment implements BlockEnchantment {

    public SmeltingEnchantment() {
        super(
                ModEnchantmentKeys.SMELTING,
                ItemTags.PICKAXES,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlot.MAINHAND,
                5,
                1,
                5,
                8,
                20,
                8,
                1
        );
    }

    @Override
    public void onDropBlock(Level worldLevel, Player breaker, List<ItemEntity> drops, BlockPos pos, ItemStack tool, int level) {
        drops.forEach(drop -> {
            ItemStack original = drop.getItem();
            ItemStack smelted = smeltItem(worldLevel, original);

            if(!ItemStack.matches(original, smelted)) {
                drop.setItem(smelted);
                breaker.getCommandSenderWorld().addParticle(ParticleTypes.FLAME, drop.getX(), drop.getY() + 0.5, drop.getZ(), 5, 0.2, 0.2);
            }
        });
    }

    public ItemStack smeltItem(Level level, ItemStack input) {
        if(input.isEmpty()) return input;

        SingleRecipeInput recipeInput = new SingleRecipeInput(input);

        Optional<RecipeHolder<SmeltingRecipe>> recipeHolder = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, recipeInput, level);

        if(recipeHolder.isPresent()) {
            SmeltingRecipe recipe = recipeHolder.get().value();
            ItemStack result = recipe.getResultItem(level.registryAccess()).copy();
            result.setCount(input.getCount());
            return result;
        }

        return input;
    }

    @Override
    public void onBreakBlock(Player player, BlockPos pos, ItemStack tool, int level) {}
}
