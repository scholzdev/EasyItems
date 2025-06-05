package dev.florianscholz.easyitems.renderer;

import net.minecraft.world.entity.LivingEntity;

public class CurrentlyRenderingEntity {
    private static final ThreadLocal<LivingEntity> CURRENT = new ThreadLocal<>();

    public static void set(LivingEntity entity) {
        CURRENT.set(entity);
    }

    public static LivingEntity get() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }
}