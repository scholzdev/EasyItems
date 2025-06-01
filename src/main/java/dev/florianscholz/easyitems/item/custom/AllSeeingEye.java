package dev.florianscholz.easyitems.item.custom;

import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeMenu;
import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class AllSeeingEye extends Item implements MenuProvider {

    protected final ContainerData data;
    private static final int INPUT_SLOT = 0;
    ItemLike targetItem;

    public AllSeeingEye(Properties properties) {
        super(properties);
        data = new ContainerData() {
            @Override
            public int get(int index) {
                return 0;
            }

            @Override
            public void set(int index, int value) {
                // no idea what to do here
            }

            @Override
            public int getCount() {
                return 1;
            }
        };
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
    public @org.jetbrains.annotations.Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new AllSeeingEyeMenu(containerId, playerInventory, data);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Tracking: " + toTrack().getDescription().getString()));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if(!level.isClientSide()) {
            return InteractionResultHolder.fail(itemstack);
        }

        var menu = new AllSeeingEyeMenu(0, player.getInventory());
        var screen = new AllSeeingEyeScreen(menu, player.getInventory(), Component.literal("All Seeing Eye Configuration"));

        Minecraft.getInstance().setScreen(screen);

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
