package dev.florianscholz.easyitems.component;

import com.mojang.serialization.Codec;
import net.minecraft.world.item.ItemStack;

public record TargetItemComponent(ItemStack item) {
    public static final Codec<TargetItemComponent> CODEC =
            ItemStack.CODEC.xmap(TargetItemComponent::new, TargetItemComponent::item);

    public static ItemStack extract(ItemStack stack) {
        TargetItemComponent component = stack.get(ModDataComponents.TARGET_ITEM);
        return component != null ? component.item() : ItemStack.EMPTY;
    }

}