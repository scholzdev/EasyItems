package dev.florianscholz.easyitems.item;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.food.ModFoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EasyItems.MOD_ID);

    public static final DeferredItem<Item> SOUL = ITEMS.register("soul", () -> new Item(new Item.Properties().food(ModFoodProperties.SOUL)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}