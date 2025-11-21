package dev.florianscholz.easyitems.util;

import dev.florianscholz.easyitems.EasyItems;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Optional;

public class EnchantmentUtils {
    public static int getLevel(ItemStack itemStack, ResourceKey<Enchantment> expectedKey) {
        ItemEnchantments enchantments = itemStack.get(DataComponents.ENCHANTMENTS);
        if (enchantments == null || enchantments.isEmpty()) return 0;

//        ResourceKey<Enchantment> expectedKey = ResourceKey.create(
//                Registries.ENCHANTMENT,
//                ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, name)
//        );

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.entrySet()) {
            Holder<Enchantment> holder = entry.getKey();
            Optional<ResourceKey<Enchantment>> key = holder.unwrapKey();

            if (key.isPresent() && key.get().equals(expectedKey)) {
                return entry.getIntValue();
            }
        }

        return 0;
    }

}