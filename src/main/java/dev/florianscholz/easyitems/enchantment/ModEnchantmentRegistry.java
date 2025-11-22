package dev.florianscholz.easyitems.enchantment;

import dev.florianscholz.easyitems.enchantment.custom.*;
import dev.florianscholz.easyitems.enchantment.types.TreeInteractionEnchantment;
import dev.florianscholz.easyitems.enchantment.types.*;
import dev.florianscholz.easyitems.enchantment.types.mobs.MobEffectEnchantment;

import java.util.ArrayList;
import java.util.List;

public class ModEnchantmentRegistry {

    private static final List<IEnchantment> ALL_ENCHANTMENTS = new ArrayList<>();

    public static final PhotosynthesisEnchantment PHOTOSYNTHESIS = register(new PhotosynthesisEnchantment());
    public static final NocturnalEnchantment METABOLISM = register(new NocturnalEnchantment());
    public static final PaybackEnchantment PAYBACK = register(new PaybackEnchantment());
    public static final BerserkEnchantment BERSERK = register(new BerserkEnchantment());
    public static final SmeltingEnchantment SMELTING = register(new SmeltingEnchantment());
    public static final DoubleJumpEnchantment DOUBLE_JUMP = register(new DoubleJumpEnchantment());
    public static final EnlightenmentEnchantment ENLIGHTENMENT = register(new EnlightenmentEnchantment());
    public static final ReaperEnchantment REAPER = register(new ReaperEnchantment());
    public static final GourmetEnchantment GOURMET = register(new GourmetEnchantment());
    public static final VenomEnchantment VENOM = register(new VenomEnchantment());
    public static final PoisonWardEnchantment POISON_WARD = register(new PoisonWardEnchantment());
    public static final ProdigyEnchantment PRODIGY = register(new ProdigyEnchantment());
    public static final LumberjackEnchantment LUMBERJACK = register(new LumberjackEnchantment());

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

    public static List<MobEffectEnchantment> getAllMobEffects() {
        return ALL_ENCHANTMENTS.stream()
                .filter(MobEffectEnchantment.class::isInstance)
                .map(MobEffectEnchantment.class::cast)
                .toList();
    }

    public static List<XPEnchantment> getAllXP() {
        return ALL_ENCHANTMENTS.stream()
                .filter(XPEnchantment.class::isInstance)
                .map(XPEnchantment.class::cast)
                .toList();
    }

    public static List<TreeInteractionEnchantment> getAllTree() {
        return ALL_ENCHANTMENTS.stream()
                .filter(TreeInteractionEnchantment.class::isInstance)
                .map(TreeInteractionEnchantment.class::cast)
                .toList();
    }

}