package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.component.TargetItemComponent;
import dev.florianscholz.easyitems.item.custom.AllSeeingEye;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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

        int range = 50;

//        ItemLike helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();

        ItemStack inHand = player.getItemInHand(InteractionHand.MAIN_HAND);

        if(!(inHand.getItem() instanceof AllSeeingEye allSeeingEye)) {
            cir.setReturnValue(false);
        } else {
            ItemStack content = TargetItemComponent.extract(inHand);

            EntityType toTrack = allSeeingEye.toTrack(content);

            if(toTrack == null) cir.setReturnValue(false);

            if(entity.getType() == toTrack) {
                double distance = entity.position().distanceTo(player.position());
                if (distance <= range) {
                    cir.setReturnValue(true);
                } else {
                    cir.setReturnValue(false);
                }
            }

        }
    }
}
