package net.bells.eldencraft.biome;

import net.bells.eldencraft.EldenCraft;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EldenBiomes {
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
        BiomeDefaultFeatures.addIcebergs(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultCarvers(generationSettings);
        BiomeDefaultFeatures.addDefaultLakes(generationSettings);
        BiomeDefaultFeatures.addBambooVegetation(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_LAVA);

        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        //generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  ModConfiguredFeatures.REDWOOD
          //      .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
            //    .decorated(FeatureDecorator.COUNT_EXTRA
              //          .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
                //                2, 0.1f, 1))));

        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.NONE)
                .depth(0.125F).scale(1.0F).temperature(0.8F).downfall(0.4F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3f76e4).waterFogColor(0x050533)
                        .fogColor(0xc0d8ff).skyColor(0x77adff)
                        .build()).mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build()).build();
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);
    }
}
