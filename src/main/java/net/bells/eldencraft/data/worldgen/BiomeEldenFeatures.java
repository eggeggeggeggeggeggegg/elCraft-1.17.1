package net.bells.eldencraft.data.worldgen;

import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class BiomeEldenFeatures{

    public static void addPilePoo(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, EldenFeatures.PILE_POO);
    }
    public static void addEldenGrass(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, EldenFeatures.ELDEN_GRASS);
    }

}