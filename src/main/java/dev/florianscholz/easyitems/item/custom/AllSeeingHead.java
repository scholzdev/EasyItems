package dev.florianscholz.easyitems.item.custom;

import dev.florianscholz.easyitems.component.TargetItemComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import java.lang.annotation.Target;
import java.util.List;

public class AllSeeingHead extends ArmorItem {

    private final AllSeeingEye allSeeingEye;

    public AllSeeingHead(AllSeeingEye eye, Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
        this.allSeeingEye = eye;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        ItemStack contents = TargetItemComponent.extract(stack);

        if(contents != null) {
            System.out.println(contents.getHoverName());
        }

        this.allSeeingEye.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public AllSeeingEye getAllSeeingEye() {
        return allSeeingEye;
    }
}
