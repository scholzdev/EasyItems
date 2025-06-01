package dev.florianscholz.easyitems.block.entity;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, EasyItems.MOD_ID);

    public static final Supplier<BlockEntityType<MaterialInfuserBlockEntity>> MATERIAL_INFUSER_BE = BLOCK_ENTITIES.register("material_infuser_be", () -> BlockEntityType.Builder.of(
            MaterialInfuserBlockEntity::new, ModBlocks.MATERIAL_INFUSER.get()).build(null)
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
