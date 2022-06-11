package net.bells.eldencraft.biome;

import net.bells.eldencraft.EldenCraft;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class ModSurfaceConfigs {
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderBaseConfiguration> ELDEN_SURFACE_BUILDER =
            register("elden_surface", SurfaceBuilder.DEFAULT.configured(new SurfaceBuilderBaseConfiguration(
                    Blocks.ACACIA_LOG.defaultBlockState(),
                    Blocks.ACACIA_PLANKS.defaultBlockState(),
                    Blocks.SANDSTONE.defaultBlockState())));

    private static <T extends SurfaceBuilderBaseConfiguration> ConfiguredSurfaceBuilder<T> register(String name,
                                                                                                    ConfiguredSurfaceBuilder<T> surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER,
                new ResourceLocation(EldenCraft.MOD_ID, name), surfaceBuilder);
    }
}
