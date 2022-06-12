package net.bells.eldencraft.gen;

import net.bells.eldencraft.block.EldenBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> LIMGRAVE_OAK = register("limgrave_oak", Feature.TREE.configured(
            new TreeConfiguration.TreeConfigurationBuilder(
                    new SimpleStateProvider(Blocks.OAK_LOG.defaultBlockState()),
                    new StraightTrunkPlacer(8, 4, 3),
                    new SimpleStateProvider(Blocks.DARK_OAK_LEAVES.defaultBlockState()),
                    new SimpleStateProvider(EldenBlocks.LIMGRAVE_OAK_SAPLING.get().defaultBlockState()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)).build()));

    private static <FC extends FeatureConfiguration>ConfiguredFeature<FC, ?> register(String name,
                                                                                      ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }
}
