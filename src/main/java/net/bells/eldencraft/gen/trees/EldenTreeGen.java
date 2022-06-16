package net.bells.eldencraft.gen.trees;

import net.bells.eldencraft.biome.EldenBiomes;
import net.bells.eldencraft.gen.ModConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.Features;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class EldenTreeGen {

    public static void generateTrees(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());

        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(event.getName().equals(EldenBiomes.LIMGRAVE.getId())) {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStep.Decoration.RAW_GENERATION); //VEG

            base.add(() -> ModConfiguredFeatures.LIMGRAVE_OAK
                    .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)//.decorated(Features.Decorators.HEIGHTMAP_TOP_SOLID)

                    .decorated(FeatureDecorator.COUNT_EXTRA
                            .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                                    5, 0.8f, 10)))); //2, 0.8, 10
            base.add(() -> ModConfiguredFeatures.LIMGRAVE_BIRCH
                    .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)//.decorated(Features.Decorators.HEIGHTMAP_TOP_SOLID)

                    .decorated(FeatureDecorator.COUNT_EXTRA
                            .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                                    5, 0.4f, 10)))); //2, 0.8, 10
        }

    }
}
