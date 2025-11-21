package dev.florianscholz.easyitems.tracker;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PaybackTracker {

    public static final Map<UUID, Long> PAYBACK_TIME = new HashMap<>();

    public static boolean isActive(Player player) {
        long now = System.currentTimeMillis();
        return PAYBACK_TIME.getOrDefault(player.getUUID(), 0L) > now;
    }

    public static void activate(Player player, int seconds) {
        long now = System.currentTimeMillis();
        PAYBACK_TIME.put(player.getUUID(), now + seconds * 1000L);
    }
}
