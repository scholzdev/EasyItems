package dev.florianscholz.easyitems.util;

import dev.florianscholz.easyitems.EasyItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        public static final TagKey<Block> NEEDS_PICKAXE_TOOL = createTag("needs_pickaxe_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        public static final TagKey<Item> PICKAXE_ITEMS = createTag("pickaxe_items");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, name));
        }
    }

    public static class Enchantments {
        public static final TagKey<Enchantment> FIRE_ASPECT = createTag("fire_aspect_enchantmens");
        public static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace(name));
        }
    }
}
