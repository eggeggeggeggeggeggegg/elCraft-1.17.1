package net.bells.eldencraft.gen;

import net.bells.eldencraft.EldenCraft;
import net.bells.eldencraft.gen.trees.EldenTreeGen;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = EldenCraft.MOD_ID)
public class WorldGenEvents {
    @SubscribeEvent
    public static void ModWorldGeneration(final BiomeLoadingEvent event) {
        EldenTreeGen.generateTrees(event);
    }
}
