package dev.florianscholz.easyitems.datagen;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.item.ModItems;
import dev.florianscholz.easyitems.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, EasyItems.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.BISMUTH.get())
                .add(ModItems.RAW_BISMUTH.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

        tag(ModTags.Items.PICKAXE_ITEMS)
                .add(ModItems.CRAFTABLE_IRON_PICKAXE.get())
                .add(ModItems.CRAFTABLE_GOLDEN_PICKAXE.get())
                .add(ModItems.CRAFTABLE_DIAMOND_PICKAXE.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.CRAFTABLE_IRON_PICKAXE.get())
                .add(ModItems.CRAFTABLE_GOLDEN_PICKAXE.get())
                .add(ModItems.CRAFTABLE_DIAMOND_PICKAXE.get());
    }
}