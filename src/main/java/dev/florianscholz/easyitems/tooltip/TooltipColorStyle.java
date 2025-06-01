package dev.florianscholz.easyitems.tooltip;

import net.minecraft.ChatFormatting;

public enum TooltipColorStyle {

    BASE, GOLD, VARYING;

    public ChatFormatting getColorForDurability(ChatFormatting baseColor, int durability, int maxDurability) {
        switch(this) {
            case BASE:
                return baseColor;
            case GOLD:
                return ChatFormatting.GOLD;
            case VARYING:
                return durability >= 0.4f * maxDurability ? ChatFormatting.GREEN : durability >= 0.1f * maxDurability ? ChatFormatting.GOLD : ChatFormatting.RED;
            default:
                return null;
        }
    }

}
