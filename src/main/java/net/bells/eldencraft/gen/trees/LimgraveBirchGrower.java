package net.bells.eldencraft.gen.trees;

import net.bells.eldencraft.gen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import javax.annotation.Nullable;
import java.util.Random;

public class LimgraveBirchGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeConfiguration, ?> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return (ConfiguredFeature<TreeConfiguration, ?>) ModConfiguredFeatures.LIMGRAVE_BIRCH;
    }
}
