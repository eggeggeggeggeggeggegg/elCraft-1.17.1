package net.bells.eldencraft.biome;

import com.google.common.collect.ImmutableList;
import net.bells.eldencraft.EldenCraft;
import net.bells.eldencraft.gen.ModConfiguredFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EldenBiomes extends Biomes {

    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, EldenCraft.MOD_ID);

    public static final RegistryObject<Biome> ELDEN_LAND = BIOMES.register("elden_land",
            EldenBiomes::createEldenLand);


    private static Biome createEldenLand() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(ModSurfaceConfigs.ELDEN_SURFACE_BUILDER);

        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultCarvers(generationSettings);
        BiomeDefaultFeatures.addPlainGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultLakes(generationSettings);
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        //BiomeDefaultFeatures.addWaterTrees(generationSettings);

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(ModConfiguredFeatures.LIMGRAVE_OAK.weighted(0.025641026F), ModConfiguredFeatures.LIMGRAVE_OAK.weighted(0.30769232F), ModConfiguredFeatures.LIMGRAVE_OAK.weighted(0.33333334F)), Features.SPRUCE)).decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(10, 0.1F, 1))));;
        //BiomeDefaultFeatures.addBambooVegetation(generationSettings);

        generationSettings.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_LAVA);

        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        //generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  ModConfiguredFeatures.LIMGRAVE_OAK
         //       .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
           //     .decorated(FeatureDecorator.COUNT_EXTRA
             //           .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
               //                 2, 0.8f, 1))));


        int skycolor = 0x435e5c;
        int fogcolor = 0xadc4c2;
        int folcolor = 0xf5cc27;
        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.NONE)
                .depth(0.125F).scale(1.0F).temperature(0.8F).downfall(0.4F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        // COLORS
                        // WATER
                        .waterColor(0x9bbdbf)
                        .waterFogColor(0x9bbdbf)
                        // FOG
                        .fogColor(fogcolor)
                        .skyColor(skycolor)
                        // GRASS + LEAVES
                        .grassColorOverride(0x93b565)
                        .foliageColorOverride(folcolor)

                        .build()).mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    public static void register(IEventBus eventBus)
    {
        BIOMES.register(eventBus);
    }
}
