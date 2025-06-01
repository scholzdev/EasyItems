package dev.florianscholz.easyitems.item.custom;

import dev.florianscholz.easyitems.item.ModToolTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public enum PickaxeHeadType {
    IRON(new PickaxeHeadStats(ModToolTiers.IRON, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.IRON, 1.F, -2.8F)))),
    GOLD(new PickaxeHeadStats(ModToolTiers.GOLD, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.GOLD, 1.F, -2.8F)))),
    DIAMOND(new PickaxeHeadStats(ModToolTiers.DIAMOND, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.DIAMOND, 1.F, -2.8F))));
    private final PickaxeHeadStats stats;

    PickaxeHeadType(PickaxeHeadStats stats) {
        this.stats = stats;
    }

    public Item.Properties getProperties() {
        return stats.itemProperties();
    }

    public PickaxeHeadStats stats() {
        return stats;
    }
}
