package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.component.TargetItemComponent;
import dev.florianscholz.easyitems.item.custom.AllSeeingHelmet;
import dev.florianscholz.easyitems.util.EnchantmentUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow @Nullable public LocalPlayer player;

    @Inject(method = "shouldEntityAppearGlowing", at = @At("HEAD"), cancellable = true)
    public void use(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity == null) return;

        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

        if(!(helmet.getItem() instanceof AllSeeingHelmet allSeeingHelmet)) {
            cir.setReturnValue(false);
        } else {
            ItemStack content = TargetItemComponent.extract(helmet);

            if(content == null) cir.setReturnValue(false);

            EntityType targetType = allSeeingHelmet.toTrack(content);

            if(targetType == null) cir.setReturnValue(false);

            if(entity.getType() == targetType) {
                double distance = entity.position().distanceTo(player.position());

                int enchantmentRange = 1;
                int totalRange = 50 + (10 * enchantmentRange);

                if (distance <= totalRange) {
                    cir.setReturnValue(true);
                } else {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
