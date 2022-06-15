package net.bells.eldencraft.biome;

import net.bells.eldencraft.EldenCraft;
import net.bells.eldencraft.data.worldgen.BiomeEldenFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EldenBiomes extends Biomes {

    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, EldenCraft.MOD_ID);

    public static final RegistryObject<Biome> ELDEN_LAND = BIOMES.register("elden_land",
            EldenBiomes::createEldenLand);
    public static final RegistryObject<Biome> ALTUS_PLATEAU = BIOMES.register("altus_plateau",
            EldenBiomes::createAltusPlateau);



    //public static final ConfiguredFeature<?, ?> LIMGRAVE_TREES = register("treeman", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(Features.FANCY_OAK.weighted(0.1F)), Features.OAK)).decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1))));
    private static Biome createEldenLand() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.monsters(spawnSettings, 95, 5, 100);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();
        generationSettings.surfaceBuilder(ModSurfaceConfigs.ELDEN_SURFACE_BUILDER);

        BiomeEldenFeatures.addEldenGrass(generationSettings);

        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultCarvers(generationSettings);
        BiomeDefaultFeatures.addPlainGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings);
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        //BiomeDefaultFeatures.addBambooVegetation(generationSettings);

        BiomeDefaultFeatures.addPlainVegetation(generationSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);

        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        //generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,  ModConfiguredFeatures.LIMGRAVE_OAK
         //       .decorated(Features.Decorators.HEIGHTMAP_WITH_TREE_THRESHOLD_SQUARED)
           //     .decorated(FeatureDecorator.COUNT_EXTRA
             //           .configured(new FrequencyWithExtraChanceDecoratorConfiguration(
               //                 2, 0.8f, 1))));


        int skycolor = 0x435e5c;
        int fogcolor = 0xadc4c2;
        int folcolor = 0xf5cc27;
        return (new Biome.BiomeBuilder()).precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.NONE)
                .depth(0.125F).scale(1.0F).temperature(0.8F).downfall(0.4F) //depth: 0.125, scale: 1.0f, temp 0.8, downfall 0.4
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
    private static Biome createAltusPlateau() {
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
        BiomeDefaultFeatures.addWaterTrees(generationSettings);
        //BiomeDefaultFeatures.addBambooVegetation(generationSettings);

        BiomeDefaultFeatures.addPlainVegetation(generationSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeDefaultFeatures.addForestFlowers(generationSettings);

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
                .depth(0.025F).scale(0.1F).temperature(0.8F).downfall(0.4F)
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
