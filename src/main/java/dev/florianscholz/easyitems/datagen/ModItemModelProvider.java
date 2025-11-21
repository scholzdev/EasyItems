package dev.florianscholz.easyitems.datagen;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.item.ModItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EasyItems.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());
        basicItem(ModItems.TUNGSTEN.get());
        basicItem(ModItems.INFUSED_TUNGSTEN.get());
        basicItem(ModItems.RAW_TUNGSTEN.get());
        basicItem(ModItems.ILMENITE.get());
        basicItem(ModItems.RAW_ILMENITE.get());

        // Blueprints
        basicItem(ModItems.BLUEPRINT_PAPER.get());
        basicItem(ModItems.BLUEPRINT_PICKAXE.get());

        basicItem(ModItems.PICKAXE_HEAD_IRON.get());
        basicItem(ModItems.PICKAXE_HEAD_GOLD.get());
        basicItem(ModItems.PICKAXE_HEAD_DIAMOND.get());
//        basicItem(ModItems.PICKAXE_HEAD_NETHERITE.get());

        basicItem(ModItems.CRAFTABLE_IRON_PICKAXE.get());
        basicItem(ModItems.CRAFTABLE_GOLDEN_PICKAXE.get());
        basicItem(ModItems.CRAFTABLE_DIAMOND_PICKAXE.get());

        basicItem(ModItems.TUNGSTEN_HEAD.get());
        basicItem(ModItems.ALL_SEEING_HELMET.get());
        basicItem(ModItems.ALL_SEEING_EYE.get());
        basicItem(ModItems.SOUL.get());
    }
}
