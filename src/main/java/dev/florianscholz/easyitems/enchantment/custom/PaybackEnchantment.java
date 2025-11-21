package dev.florianscholz.easyitems.enchantment.custom;

import dev.florianscholz.easyitems.enchantment.ModEnchantmentKeys;
import dev.florianscholz.easyitems.enchantment.types.BaseEnchantment;
import dev.florianscholz.easyitems.enchantment.types.CombatEnchantment;
import dev.florianscholz.easyitems.enchantment.types.DamageModifierEnchantment;
import dev.florianscholz.easyitems.tracker.PaybackTracker;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/* deaths door:
    - one hp
*/
public class PaybackEnchantment extends BaseEnchantment implements CombatEnchantment, DamageModifierEnchantment {

    public PaybackEnchantment() {
        super(
                ModEnchantmentKeys.PAYBACK,
                ItemTags.SWORD_ENCHANTABLE,
                EquipmentSlotGroup.MAINHAND,
                EquipmentSlot.MAINHAND,
                5,
                3,
                5,
                8,
                20,
                8,
                1
        );
    }

    @Override
    public void onHurt(Player attacker, Entity victim, int level, float damage) {
        if(!(victim instanceof Player player)) return;

        int duration = 2 * level;
        PaybackTracker.activate(player, duration);
        System.out.println("Payback activated for " + duration + " seconds");
    }

    @Override
    public float modifyOutgoingDamage(Player attacker, LivingEntity target, float originalDamage, int level, ItemStack weapon) {
        if(!(target instanceof Player player)) return originalDamage;

        return PaybackTracker.isActive(player) ? 1.5f : originalDamage;
    }
}
