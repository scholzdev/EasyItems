package dev.florianscholz.easyitems.item;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EasyItems.MOD_ID);

    public static final Supplier<CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("easyitems_items_tab", () ->
                CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TUNGSTEN.get()))
                        .title(Component.translatable("creativetab.easyitems.items_tab"))
                        .displayItems((itemDisplayParameters, output) -> {
                            output.accept(ModItems.RAW_BISMUTH);
                            output.accept(ModItems.BISMUTH);
                            output.accept(ModItems.RAW_TUNGSTEN);
                            output.accept(ModItems.TUNGSTEN);
                            output.accept(ModItems.INFUSED_TUNGSTEN);
                            output.accept(ModItems.ILMENITE);
                            output.accept(ModItems.RAW_ILMENITE);

                            output.accept(ModItems.BLUEPRINT_PAPER);
                            output.accept(ModItems.BLUEPRINT_PICKAXE);

                            output.accept(ModItems.PICKAXE_HEAD_IRON);
                            output.accept(ModItems.PICKAXE_HEAD_GOLD);
                            output.accept(ModItems.PICKAXE_HEAD_DIAMOND);

                            output.accept(ModItems.CRAFTABLE_IRON_PICKAXE);
                            output.accept(ModItems.CRAFTABLE_GOLDEN_PICKAXE);
                            output.accept(ModItems.CRAFTABLE_DIAMOND_PICKAXE);
                            output.accept(ModItems.TUNGSTEN_HEAD);
                            output.accept(ModItems.ALL_SEEING_EYE);
                        }).build());

    public static final Supplier<CreativeModeTab> BLOCKS_TAB = CREATIVE_MODE_TABS.register("easyitems_blocks_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TUNGSTEN_ORE.get()))
                    .title(Component.translatable("creativetab.easyitems.blocks_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, "easyitems_items.tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.TUNGSTEN_ORE);
                        output.accept(ModBlocks.MATERIAL_INFUSER);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
