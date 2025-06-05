package dev.florianscholz.easyitems.capabilities.Item;

import dev.florianscholz.easyitems.component.ModDataComponents;
import dev.florianscholz.easyitems.component.TargetItemComponent;
import net.minecraft.world.item.ItemStack;

public class ModItemItemStorage extends ModItemStorage {

    public ItemStack stack;

    public ModItemItemStorage(ItemStack outerStack) {
        super(1);
        this.stack = outerStack;

        TargetItemComponent component = outerStack.get(ModDataComponents.TARGET_ITEM);
        if (component != null && !component.item().isEmpty()) {
            setStackInSlot(0, component.item().copy());
        }
    }

    @Override
    protected void onContentsChanged(int slot) {
        if (stack == null) return;

        ItemStack content = getStackInSlot(slot);
        if (!content.isEmpty()) {
            stack.set(ModDataComponents.TARGET_ITEM, new TargetItemComponent(content.copy()));
        } else {
            stack.remove(ModDataComponents.TARGET_ITEM);
        }
    }
}