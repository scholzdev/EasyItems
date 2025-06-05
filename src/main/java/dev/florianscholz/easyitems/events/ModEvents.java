package dev.florianscholz.easyitems.events;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.capabilities.Item.ModItemItemStorage;
import dev.florianscholz.easyitems.item.ModItems;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = EasyItems.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(Capabilities.ItemHandler.ITEM, (stack, ctx) -> new ModItemItemStorage(stack), ModItems.ALL_SEEING_EYE.get());
    }
}
