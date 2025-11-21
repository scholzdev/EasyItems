package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void onFinishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity, CallbackInfoReturnable<ItemStack> cir) {

        if(!(livingEntity instanceof Player player)) return;
        if(stack.getItem().getFoodProperties(stack, player) == null) return;

        ItemStack playerChest = player.getItemBySlot(EquipmentSlot.CHEST);

        int gourmetLevel = EnchantmentUtils.getLevel(playerChest, ModEnchantmentKeys.GOURMET);

        if(gourmetLevel <= 0) return;

        FoodProperties food = stack.getItem().getFoodProperties(stack, player);
        if(food == null) return;

        int nutrition = food.nutrition();
        float saturation = food.saturation();

        int newNutrition = (int) Math.ceil(nutrition * (1 + (gourmetLevel * 0.1)));
        float newSaturation = (float) (saturation * (1 + (gourmetLevel * 0.1)));

        player.getFoodData().eat(newNutrition, newSaturation);

        food.effects().forEach(possibleEffect -> {
            player.addEffect(possibleEffect.effect());
        });

        stack.shrink(1);

        cir.setReturnValue(stack);
    }
}
