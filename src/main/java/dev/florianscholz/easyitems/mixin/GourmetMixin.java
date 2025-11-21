package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class GourmetMixin {

    @Inject(method = "getUseDuration", at = @At("HEAD"), cancellable = true)
    private void onGetUseDuration(ItemStack stack, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {

        ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);

        int level = EnchantmentUtils.getLevel(chest, ModEnchantmentKeys.GOURMET);

        if(level <= 0) return;

        FoodProperties foodProperties = stack.get(DataComponents.FOOD);

        if(foodProperties == null) return;

        cir.setReturnValue(foodProperties.eatDurationTicks() * 2);
        cir.cancel();
    }


}