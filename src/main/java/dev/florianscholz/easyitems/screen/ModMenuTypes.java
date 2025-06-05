package dev.florianscholz.easyitems.screen;

import dev.florianscholz.easyitems.EasyItems;
import dev.florianscholz.easyitems.screen.custom.AllSeeingEyeMenu;
import dev.florianscholz.easyitems.screen.custom.MaterialInfuserMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, EasyItems.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<MaterialInfuserMenu>> MATERIAL_INFUSER_MENU =
            registerMenuType("material_infuser_menu", MaterialInfuserMenu::new);

//    public static final DeferredHolder<MenuType<?>, MenuType<AllSeeingEyeMenu>> ALL_SEEING_EYE_MENU2 =
//            registerMenuType("all_seeing_eye_menu", AllSeeingEyeMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<AllSeeingEyeMenu>> ALL_SEEING_EYE_MENU =
            MENUS.register("all_seeing_eye_menu", () -> IMenuTypeExtension.create(AllSeeingEyeMenu::fromNetwork));

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                               IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}