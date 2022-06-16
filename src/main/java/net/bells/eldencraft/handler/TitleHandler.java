package net.bells.eldencraft.handler;

import net.bells.eldencraft.EldenCraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.BrandingControl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EldenCraft.MOD_ID, value = Dist.CLIENT)
public class TitleHandler {
    private static boolean hasLoaded = false;

    @SubscribeEvent
    public static void openMainMenu(final GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof TitleScreen) {
            init();
        }
    }

    public static void init() {
        if (!hasLoaded) {
            try {
                BrandingControl brandingControl = new BrandingControl();

                Field f = BrandingControl.class.getDeclaredField("brandings");
                f.setAccessible(true);

                Method computeBranding = BrandingControl.class.getDeclaredMethod("computeBranding");
                computeBranding.setAccessible(true);
                computeBranding.invoke(null);

                List<String> brands = new ArrayList<>((List<String>) f.get(brandingControl));
                List<String> newBrands = new ArrayList<>();

                f.set(brandingControl, newBrands);
                newBrands.addAll(ConfigHandler.CUSTOM_TEXT.titleScreenText.get());

                if (ConfigHandler.GENERAL.titleScreenMCVersion.get()) {
                    newBrands.add(brands.get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            hasLoaded = true;
        }
    }
}
