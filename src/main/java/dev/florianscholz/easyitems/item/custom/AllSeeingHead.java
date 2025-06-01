package dev.florianscholz.easyitems.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class AllSeeingHead extends ArmorItem {

    private final AllSeeingEye allSeeingEye;

    public AllSeeingHead(AllSeeingEye eye, Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
        this.allSeeingEye = eye;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        this.allSeeingEye.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public AllSeeingEye getAllSeeingEye() {
        return allSeeingEye;
    }
}
