package net.bells.eldencraft.handler;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class ConfigHandler {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final CustomText CUSTOM_TEXT = new CustomText(BUILDER);
    public static final General GENERAL = new General(BUILDER);

    public static class CustomText {
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> titleScreenText;
        static final String DEFAULT_TEXT = "cobblestone.minehut.gg";

        CustomText(ForgeConfigSpec.Builder builder) {
            builder.push("custom text");
            titleScreenText = builder
                    .defineList("text", Lists.newArrayList(DEFAULT_TEXT), o -> o instanceof String);
            builder.pop();
        }
    }

    public static class General {
        public final ForgeConfigSpec.BooleanValue titleScreenMCVersion;

        General(ForgeConfigSpec.Builder builder) {
            builder.push("versions");
            titleScreenMCVersion = builder
                    .comment("Show which version of Minecraft the client is currently running.")
                    .translation("boutta hop on the cob ngl")
                    .define("showMcVersion", true);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spec = BUILDER.build();
}