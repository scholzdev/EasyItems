package dev.florianscholz.easyitems;

import dev.florianscholz.easyitems.block.ModBlocks;
import dev.florianscholz.easyitems.block.entity.ModBlockEntities;
import dev.florianscholz.easyitems.component.ModDataComponents;
import dev.florianscholz.easyitems.enchantment.ModEnchantmentEffects;
import dev.florianscholz.easyitems.item.ModCreativeModeTabs;
import dev.florianscholz.easyitems.item.ModItems;
import dev.florianscholz.easyitems.loot.ModLootModifiers;
import dev.florianscholz.easyitems.recipe.ModRecipes;
import dev.florianscholz.easyitems.screen.ModMenuTypes;
import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeScreen;
import dev.florianscholz.easyitems.screen.custom.MaterialInfuserScreen;
import dev.florianscholz.easyitems.tooltip.TooltipStyle;
import dev.florianscholz.easyitems.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(EasyItems.MOD_ID)
public class EasyItems
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "easyitems";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public EasyItems(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModDataComponents.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation getId(String path) {
        if (path.contains(":")) {
            if (path.startsWith(EasyItems.MOD_ID)) {
                return ResourceLocation.tryParse(path);
            } else {
                throw new IllegalArgumentException("path contains namespace other than " + EasyItems.MOD_ID);
            }
        }
        return ResourceLocation.fromNamespaceAndPath(EasyItems.MOD_ID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        if(event.getItemStack().is(ModTags.Items.PICKAXE_ITEMS)) {
            int maxDurability = event.getItemStack().getMaxDamage();
            int durability = maxDurability - event.getItemStack().getDamageValue();
            TooltipStyle style = TooltipStyle.NUMBERS;
            style.appendTooltip(event.getToolTip(), durability, maxDurability);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }

        @SubscribeEvent
        public static void registerScreen(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.MATERIAL_INFUSER_MENU.get(), MaterialInfuserScreen::new);
            event.register(ModMenuTypes.ALL_SEEING_EYE_MENU.get(), AllSeeingEyeScreen::new);
        }
    }
}
