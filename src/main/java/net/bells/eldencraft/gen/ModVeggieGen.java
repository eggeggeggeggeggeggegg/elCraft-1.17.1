package net.bells.eldencraft.gen;

import net.bells.eldencraft.biome.EldenBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.function.Supplier;

public class ModVeggieGen {
    public static void generateFlowers(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());

        if(event.getName().equals(EldenBiomes.LIMGRAVE.getId())) {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.TOP_LAYER_MODIFICATION);

            base.add(() -> ModConfiguredFeatures.ELDEN_PATCH);
        }
    }
}
