package dev.florianscholz.easyitems.mixin;

import dev.florianscholz.easyitems.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Inject(method = "renderHeart", at = @At("HEAD"), cancellable = true)
    private void drawCustomHeart(GuiGraphics guiGraphics, Gui.HeartType heartType, int x, int y, boolean hardcore, boolean halfHeart, boolean blinking, CallbackInfo ci) {
        if(minecraft.player.hasEffect(ModEffects.VISUAL_WITHER)) {
            guiGraphics.blitSprite(Gui.HeartType.WITHERED.getSprite(hardcore, blinking, halfHeart), x, y, 9, 9);
            ci.cancel();
        }
    }
}
