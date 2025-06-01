package dev.florianscholz.easyitems.item.custom;

import dev.florianscholz.easyitems.screen.ModMenuTypes;
import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.datafix.fixes.ItemStackSpawnEggFix;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public class AllSeeingEye extends Item implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
        }
    };

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
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
//        return new AllSeeingEyeMenu(containerId, playerInventory, );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal("Tracking: " + toTrack().getDescription().getString()));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    public ItemStack getTargetItem() {
        return itemHandler.getStackInSlot(INPUT_SLOT);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if(level.isClientSide()) {
            return  InteractionResultHolder.consume(itemstack);
        }

        player.openMenu(this, buff -> ItemStack.STREAM_CODEC.encode(buff, itemstack));

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}
