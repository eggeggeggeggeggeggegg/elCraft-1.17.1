package net.bells.eldencraft.biome;

import net.bells.eldencraft.EldenCraft;
import net.bells.eldencraft.block.EldenBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class ModSurfaceConfigs {
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> ELDEN_SURFACE_BUILDER =
            register("elden_surface", SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderBaseConfiguration(
                    Blocks.GRASS_BLOCK.defaultBlockState(),
                    Blocks.ANDESITE.defaultBlockState(),
                    //new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(EldenBlocks.ELDEN_FLOWER.get().defaultBlockState(), 5).add(EldenBlocks.ELDEN_GRASS.get().defaultBlockState(), 10).build()),
                    Blocks.COARSE_DIRT.defaultBlockState())));//,

    private static <T extends SurfaceBuilderBaseConfiguration> ConfiguredSurfaceBuilder<T> register(String name,
                                                                                                    ConfiguredSurfaceBuilder<T> surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(EldenCraft.MOD_ID, name), surfaceBuilder);
    }
}
