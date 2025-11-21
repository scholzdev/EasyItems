package dev.florianscholz.easyitems.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemEntityMappingLoader extends SimpleJsonResourceReloadListener {

    public static final Map<Item, EntityType<?>> ITEM_ENTITY_MAP = new HashMap<>();
    private static final Gson GSON = new Gson();

    public ItemEntityMappingLoader() {
        super(GSON, "item_entity_mappings");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
        ITEM_ENTITY_MAP.clear();

        for (Map.Entry<ResourceLocation, JsonElement> entry : jsonMap.entrySet()) {
            JsonObject json = entry.getValue().getAsJsonObject();

            for (Map.Entry<String, JsonElement> mapping : json.entrySet()) {
                ResourceLocation itemId = ResourceLocation.tryParse(mapping.getKey());
                ResourceLocation entityId = ResourceLocation.tryParse(mapping.getValue().getAsString());

                if (itemId == null || entityId == null) {
                    System.err.println("❌ Invalid ResourceLocation in mapping: " + mapping.getKey() + " → " + mapping.getValue());
                    continue;
                }

                Item item = BuiltInRegistries.ITEM.get(itemId);
                EntityType<?> entity = BuiltInRegistries.ENTITY_TYPE.get(entityId);

                if (item != null && entity != null) {
                    ITEM_ENTITY_MAP.put(item, entity);
                } else {
                    System.err.println("⚠ Could not find item or entity: " + itemId + " → " + entityId);
                }
            }
        }
    }


}