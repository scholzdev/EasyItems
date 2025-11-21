package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentRegistry;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightTexture.class)
public class LightTextureMixin {

    @Shadow @Final private Minecraft minecraft;

    @Redirect(method = "calculateDarknessScale", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
    private float reduceDarkness(float a, float b, LivingEntity pLiving) {
        ItemStack head = minecraft.player.getItemBySlot(EquipmentSlot.HEAD);
        int enlightenmentLevel = EnchantmentUtils.getLevel(head, ModEnchantmentKeys.ENLIGHTENMENT);
        return Math.max(a, b - (enlightenmentLevel * 0.2f));
    }

    @SuppressWarnings("DataFlowIssue")
    @ModifyVariable(method = "updateLightTexture", at = @At("LOAD"), ordinal = 7)
    private float increaseNightVision(float value) {

        ItemStack head = minecraft.player.getItemBySlot(EquipmentSlot.HEAD);

        int enlightenmentLevel = EnchantmentUtils.getLevel(head, ModEnchantmentKeys.ENLIGHTENMENT);
        return value + enlightenmentLevel * .2f;
    }

}
