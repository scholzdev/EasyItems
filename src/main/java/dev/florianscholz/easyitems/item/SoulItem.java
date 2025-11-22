package dev.florianscholz.easyitems.item;

import dev.florianscholz.easyitems.component.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class SoulItem extends Item {

    public SoulItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(stack.get(ModDataComponents.SOUL_ENTITY_TYPE) != null) {

            String entityName = stack.get(ModDataComponents.SOUL_ENTITY_TYPE);

            tooltipComponents.add(Component.literal("§1Harvested from: " + entityName));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
