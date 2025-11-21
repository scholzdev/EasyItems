package dev.florianscholz.easyitems.datagen;


import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {

    public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider);
        }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Enchantments.HEALING_EXCLUSIVE)
                .add(ModEnchantmentKeys.PHOTOSYNTHESIS)
                .add(ModEnchantmentKeys.NOCTURNAL);

        for (ResourceKey<Enchantment> key : ModEnchantmentKeys.getAllKeys()) {
            tag(EnchantmentTags.NON_TREASURE).add(key);
            tag(EnchantmentTags.TRADEABLE).add(key);
        }
    }
}