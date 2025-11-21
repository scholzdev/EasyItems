package dev.florianscholz.easyitems.enchantment.types;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface DamageModifierEnchantment extends IEnchantment {

    /**
     * Modifiziert ausgehenden Schaden (wenn Spieler angreift)
     * @return Der modifizierte Damage Multiplikator (1.0 = keine Änderung, 1.5 = +50% Schaden)
     */
    default float modifyOutgoingDamage(Player attacker, LivingEntity target, float originalDamage, int level, ItemStack weapon) {
        return 1.0f;
    }

    /**
     * Modifiziert eingehenden Schaden (wenn Spieler getroffen wird)
     * @return Der modifizierte Damage Multiplikator (0.8 = -20% Schaden genommen)
     */
    default float modifyIncomingDamage(LivingEntity victim, LivingEntity attacker, float originalDamage, int level, ItemStack armor) {
        return 1.0f;
    }
}