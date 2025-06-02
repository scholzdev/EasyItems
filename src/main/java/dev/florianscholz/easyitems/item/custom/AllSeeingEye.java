package dev.florianscholz.easyitems.item.custom;

import dev.florianscholz.easyitems.item.ModItems;
import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AllSeeingEye extends Item implements MenuProvider {
    public AllSeeingEye(Properties properties) {
        super(properties);
    }

    @Nullable
    public EntityType toTrack() {
        return EntityType.ZOMBIE;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("All Seeing Eye Configurator");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new AllSeeingEyeMenu(containerId, playerInventory, ModItems.ALL_SEEING_EYE.toStack());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Tracking: " + toTrack().getDescription().getString()));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if(level.isClientSide()) {
            return InteractionResultHolder.consume(itemStack);
        }

        player.openMenu(this, buff -> ItemStack.STREAM_CODEC.encode(buff, itemStack));

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
