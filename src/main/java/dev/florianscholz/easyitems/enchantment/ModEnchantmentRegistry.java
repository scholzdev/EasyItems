package dev.florianscholz.easyitems.enchantment;

import dev.florianscholz.easyitems.enchantment.custom.*;
import dev.florianscholz.easyitems.enchantment.types.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class ModEnchantmentRegistry {

    private static final List<IEnchantment> ALL_ENCHANTMENTS = new ArrayList<>();

//    // Alle Enchantments
    public static final PhotosynthesisEnchantment PHOTOSYNTHESIS = register(new PhotosynthesisEnchantment());
    public static final NocturnalEnchantment METABOLISM = register(new NocturnalEnchantment());
    public static final PaybackEnchantment PAYBACK = register(new PaybackEnchantment());
    public static final BerserkEnchantment BERSERK = register(new BerserkEnchantment());
    public static final SmeltingEnchantment SMELTING = register(new SmeltingEnchantment());
    public static final DoubleJumpEnchantment DOUBLE_JUMP = register(new DoubleJumpEnchantment());
    public static final EnlightenmentEnchantment ENLIGHTENMENT = register(new EnlightenmentEnchantment());
    public static final ReaperEnchantment REAPER = register(new ReaperEnchantment());
    public static final GourmetEnchantment GOURMET = register(new GourmetEnchantment());

    public static void register(BootstrapContext<Enchantment> context, IEnchantment enchantment) {
//        var items = context.lookup(Registries.ITEM);
//        var enchantmentLookup = context.lookup(Registries.ENCHANTMENT);
//
//        Enchantment.Builder builder = Enchantment.enchantment(
//          Enchantment.definition(
//                  items.getOrThrow(enchantment.getSupportedItems()),
//                  enchantment.getWeight(),
//                  enchantment.getMaxLevel(),
//                  Enchantment.dynamicCost(enchantment.getMinCostBase(), enchantment.getMinCostPerLevel()),
//                  Enchantment.dynamicCost(enchantment.getMaxCostBase(), enchantment.getMinCostPerLevel()),
//                  enchantment.getAnvilCost(),
//                  enchantment.getSlotGroup()
//          )
//        );
//
//        if(enchantment.getExclusiveWith() != null) {
//            builder.exclusiveWith(enchantmentLookup.getOrThrow(enchantment.getExclusiveWith()));
//        }
//
//        context.register(
//                enchantment.getKey(),
//                builder.build(enchantment.getKey().location())
//        );
    }

    private static <T extends IEnchantment> T register(T enchantment) {
        ALL_ENCHANTMENTS.add(enchantment);
        return enchantment;
    }

    public static List<IEnchantment> getAllEnchantments() {
        return ALL_ENCHANTMENTS;
    }

    public static List<TickableEnchantment> getAllTickable() {
        return ALL_ENCHANTMENTS.stream()
                .filter(TickableEnchantment.class::isInstance)
                .map(TickableEnchantment.class::cast)
                .toList();
    }

    public static List<DamageModifierEnchantment> getAllDamageModifiers() {
        return ALL_ENCHANTMENTS.stream()
                .filter(DamageModifierEnchantment.class::isInstance)
                .map(DamageModifierEnchantment.class::cast)
                .toList();
    }

    public static List<CombatEnchantment> getAllCombat() {
        return ALL_ENCHANTMENTS.stream()
                .filter(CombatEnchantment.class::isInstance)
                .map(CombatEnchantment.class::cast)
                .toList();
    }

    public static List<BlockEnchantment> getAllBlock() {
        return ALL_ENCHANTMENTS.stream()
                .filter(BlockEnchantment.class::isInstance)
                .map(BlockEnchantment.class::cast)
                .toList();
    }

    public static List<InputModifierEnchantment> getAllInput() {
        return ALL_ENCHANTMENTS.stream()
                .filter(InputModifierEnchantment.class::isInstance)
                .map(InputModifierEnchantment.class::cast)
                .toList();
    }

    public static List<DeathEchantment> getAllDeath() {
        return ALL_ENCHANTMENTS.stream()
                .filter(DeathEchantment.class::isInstance)
                .map(DeathEchantment.class::cast)
                .toList();
    }

}