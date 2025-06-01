package dev.florianscholz.easyitems.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {


    @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
    public void onTeamChange(CallbackInfoReturnable<Integer> cir) {

    }
}
