package dev.florianscholz.easyitems.item;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.item.custom.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EasyItems.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TUNGSTEN = ITEMS.register("tungsten", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INFUSED_TUNGSTEN = ITEMS.register("infused_tungsten", () -> new Item(new Item.Properties()));

    // BLUEPRINTS
    public static final DeferredItem<Item> BLUEPRINT_PAPER = ITEMS.register("blueprint_paper", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLUEPRINT_PICKAXE = ITEMS.register("blueprint_pickaxe", () -> new Item(new Item.Properties()));

    // HEADS
    public static final DeferredItem<Item> PICKAXE_HEAD_IRON = ITEMS.register("pickaxe_head_iron", () -> new PickaxeHead(PickaxeHeadType.IRON));
    public static final DeferredItem<Item> PICKAXE_HEAD_GOLD = ITEMS.register("pickaxe_head_gold", () -> new PickaxeHead(PickaxeHeadType.GOLD));
    public static final DeferredItem<Item> PICKAXE_HEAD_DIAMOND = ITEMS.register("pickaxe_head_diamond", () -> new PickaxeHead(PickaxeHeadType.DIAMOND));
    //    public static final DeferredItem<Item> PICKAXE_HEAD_NETHERITE = ITEMS.register("pickaxe_head_netherite", () -> new Item(new Item.Properties()));

    // Pickaxes
    public static final DeferredItem<Item> CRAFTABLE_IRON_PICKAXE = ITEMS.register("craftable_iron_pickaxe", () -> new Pickaxe((PickaxeHead) PICKAXE_HEAD_IRON.get()));
    public static final DeferredItem<Item> CRAFTABLE_GOLDEN_PICKAXE = ITEMS.register("craftable_golden_pickaxe", () -> new Pickaxe((PickaxeHead) PICKAXE_HEAD_GOLD.get()));
    public static final DeferredItem<Item> CRAFTABLE_DIAMOND_PICKAXE = ITEMS.register("craftable_diamond_pickaxe", () -> new Pickaxe((PickaxeHead) PICKAXE_HEAD_DIAMOND.get()));

    // Ilmenite
    public static final DeferredItem<Item> ILMENITE = ITEMS.register("ilmenite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ILMENITE = ITEMS.register("raw_ilmenite", () -> new Item(new Item.Properties()));

    public static final DeferredItem<AllSeeingEye> ALL_SEEING_EYE = ITEMS.register("all_seeing_eye", () -> new AllSeeingEye(new Item.Properties()));

    // Armor
    public static final DeferredItem<ArmorItem> ALL_SEEING_TUNGSTEN_HEAD = ITEMS.register("all_seeing_tungsten_head", () -> new AllSeeingHead(ALL_SEEING_EYE.get(), ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
    public static final DeferredItem<ArmorItem> TUNGSTEN_HEAD = ITEMS.register("tungsten_head", () -> new ArmorItem(ModArmorMaterials.TUNGSTEN_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}