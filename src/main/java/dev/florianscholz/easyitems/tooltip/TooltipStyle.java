package dev.florianscholz.easyitems.tooltip;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;

public enum TooltipStyle {

    NUMBERS,
    TEXT;

    public void appendTooltip(List<Component> tooltips, int durability, int maxDurability) {
        ChatFormatting baseColor = ChatFormatting.BLUE;
        TooltipColorStyle colorStyle = TooltipColorStyle.BASE;
        ChatFormatting reactiveColor = colorStyle.getColorForDurability(baseColor, durability, maxDurability);

        switch (this) {
            case NUMBERS:
                Component durabilityComponent = Component.literal(Integer.toString(durability)).withStyle(reactiveColor);
                Component maxDurabilityComponent = Component.literal(Integer.toString(maxDurability)).withStyle(colorStyle == TooltipColorStyle.VARYING ? baseColor : reactiveColor);
                Component numbers;
                if(durability == maxDurability)
                    numbers = Component.translatable("durabilitytooltip.info.numbers.full_durability", maxDurabilityComponent).withStyle(baseColor);
                else
                    numbers = Component.translatable("durabilitytooltip.info.numbers.damaged", durabilityComponent, maxDurabilityComponent).withStyle(baseColor);
                tooltips.add(numbers);
                break;
            case TEXT:
                String translationKey = durability == maxDurability ? "durabilitytooltip.info.text.full_durability"
                        : durability >= 0.4f * maxDurability ? "durabilitytooltip.info.text.damaged"
                        : durability >= 0.1f * maxDurability ? "durabilitytooltip.info.text.severely_damaged"
                        : "durabilitytooltip.info.text.nearly_broken";
                Component tooltip = Component.translatable(translationKey).withStyle(reactiveColor);

                tooltips.add(tooltip);
                break;
        }
    }

}
