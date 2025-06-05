package dev.florianscholz.easyitems.component;

import dev.florianscholz.easyitems.EasyItems;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, EasyItems.MOD_ID);

//    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStack>> TARGET_ITEM = register("target_entity", builder -> builder.persistent(ItemStack.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TargetItemComponent>> TARGET_ITEM =
            register("target_item", builder -> builder.persistent(TargetItemComponent.CODEC));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
