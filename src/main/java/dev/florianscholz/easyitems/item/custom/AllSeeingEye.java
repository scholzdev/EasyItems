package dev.florianscholz.easyitems.item.custom;

import dev.florianscholz.easyitems.component.TargetItemComponent;
import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AllSeeingEye extends Item {

    public AllSeeingEye(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ItemStack content = TargetItemComponent.extract(stack);
        if(content == null) return;
        EntityType toTrack = toTrack(content);
        if(toTrack == null) return;
        tooltipComponents.add(toTrack.getDescription());

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Nullable
    public EntityType toTrack(ItemStack stack) {
        if (stack.getItem() == Items.GUNPOWDER) {
            return EntityType.CREEPER;
        } else if (stack.getItem() == Items.APPLE) {
            return EntityType.ZOMBIE;
        } else {
            return null;
        }
    }

    private void openMenu(Player player, ItemStack stack) {
        player.openMenu(new SimpleMenuProvider((id, inv, p) -> new AllSeeingEyeMenu(id, inv, stack), stack.getHoverName()), buff -> ItemStack.STREAM_CODEC.encode(buff, stack));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(level.isClientSide()) {
            return InteractionResultHolder.success(stack);
        } else {
            openMenu(player, stack);
            return InteractionResultHolder.consume(stack);
        }
    }
}
