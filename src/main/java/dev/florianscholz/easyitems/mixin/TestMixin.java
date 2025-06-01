package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.item.ModItems;
import dev.florianscholz.easyitems.item.custom.AllSeeingEye;
import dev.florianscholz.easyitems.item.custom.AllSeeingHead;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public abstract class TestMixin {

    @Shadow @Nullable public LocalPlayer player;

    @Shadow public abstract void disconnect();

    @Shadow private static Minecraft instance;

    @Inject(method = "shouldEntityAppearGlowing", at = @At("HEAD"), cancellable = true)
    public void use(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity == null) return;

        int range = 50;

        ItemLike helmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem();

        if(!(helmet instanceof AllSeeingHead)) {
            cir.setReturnValue(false);
        } else {
            EntityType toTrack = ((AllSeeingHead) helmet).getAllSeeingEye().toTrack();
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
