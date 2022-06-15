package net.bells.eldencraft;

import com.mojang.serialization.Codec;
import net.bells.eldencraft.biome.EldenBiomes;
import net.bells.eldencraft.block.EldenBlocks;
import net.bells.eldencraft.gen.ModBiomeGeneration;
import net.bells.eldencraft.item.EldenItems;
import net.bells.eldencraft.structure.EldenConfiguredStructures;
import net.bells.eldencraft.structure.EldenStructures;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EldenCraft.MOD_ID)
public class EldenCraft
{
    // Directly reference a log4j logger.
    public static final String MOD_ID = "eldencraft";
    public static final Logger LOGGER = LogManager.getLogger();

   //public static final ConfiguredFeature<?, ?> GLOWSTONE = register("glowstone", Feature.GLOWSTONE_BLOB.configured(FeatureConfiguration.NONE).range(Features.Decorators.FULL_RANGE).squared().count(10));

    //private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String pId, ConfiguredFeature<FC, ?> pConfiguredFeature) {
    //    return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, pId, pConfiguredFeature);
    //}

    public EldenCraft() {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //listen up
        modEventBus.addListener(this::setup);



        //frog bus
        IEventBus frogBus = MinecraftForge.EVENT_BUS;
        frogBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);
        frogBus.addListener(EventPriority.HIGH, this::biomeModification);

        // Registry time :D
        EldenBiomes.register(modEventBus);
        EldenBlocks.register(modEventBus);
        EldenItems.register(modEventBus);
        EldenStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());



        event.enqueueWork(() -> {
            EldenStructures.setupStructures();
            EldenConfiguredStructures.registerConfiguredStructures();
        });
        ModBiomeGeneration.generateBiomes();
    }
    public void biomeModification(final BiomeLoadingEvent event) {
         // add to all biomes
        LOGGER.info("Borangotango {}", event.getName());
        if(event.getName().equals(EldenBiomes.LIMGRAVE.getId())) {
            event.getGeneration().getStructures().add(() -> EldenConfiguredStructures.CONFIGURED_DIVINE_TOWER);
            event.getGeneration().getStructures().add(() -> EldenConfiguredStructures.CONFIGURED_MINOR_ERDTREE);
        }

        event.getGeneration().getStructures().add(() -> EldenConfiguredStructures.CONFIGURED_LARGE_RUINS);

        event.getGeneration().getStructures().add(() -> EldenConfiguredStructures.CONFIGURED_LIKBIL);
        event.getGeneration().getStructures().add(() -> EldenConfiguredStructures.CONFIGURED_LARGE_ARCH);
        event.getGeneration().getStructures().add(() -> EldenConfiguredStructures.CONFIGURED_SMALL_ARCH);
    }
    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onLoad(PlayerEvent.PlayerLoggedInEvent joinEvent) {
            joinEvent.getPlayer().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1999980, 1));
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent playerEvent) {
            Player player = playerEvent.player;
            ResourceLocation biomeAtPlayer = player.getCommandSenderWorld().getBiome(player.blockPosition()).getRegistryName();
            //LOGGER.info("Elden Land biome: >> {}", EldenBiomes.ELDEN_LAND.get());
            //LOGGER.info("Biome at player: >> {}", biomeAtPlayer);

            if(biomeAtPlayer.equals(EldenBiomes.LIMGRAVE.get().getRegistryName())) {
                // Enter elden land biome code
            }

            //LOGGER.info(player.position());
        }

    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class RegistryEventsClient
    {
        @SubscribeEvent
        public static void registerBlockColors(ColorHandlerEvent.Block event)
        {
            event.getBlockColors().register((p_92626_, p_92627_, p_92628_, p_92629_) -> {
                return p_92627_ != null && p_92628_ != null ? BiomeColors.getAverageGrassColor(p_92627_, p_92628_) : FoliageColor.getDefaultColor();
            }, Blocks.OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, EldenBlocks.ELDEN_GRASS.get(), EldenBlocks.ELDEN_FLOWER.get());
            //event.getBlockColors().register(, EldenBlocks.ELDEN_GRASS.get()); {
            //}
        }
        @SubscribeEvent
        public static void clientSetupEvent(final FMLClientSetupEvent eventman)
        {
            ItemBlockRenderTypes.setRenderLayer(EldenBlocks.LIMGRAVE_OAK_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(EldenBlocks.LIMGRAVE_BIRCH_SAPLING.get(), RenderType.cutout());

            ItemBlockRenderTypes.setRenderLayer(EldenBlocks.ERD_TREE_LEAVES.get(), RenderType.cutoutMipped());
            ItemBlockRenderTypes.setRenderLayer(EldenBlocks.ELDEN_GRASS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(EldenBlocks.ELDEN_FLOWER.get(), RenderType.cutout());
        }
    }

    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerLevel){
            ServerLevel serverWorld = (ServerLevel)event.getWorld();

            /*
             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
             * They will handle your structure spacing for your if you add to BuiltinRegistries.NOISE_GENERATOR_SETTINGS in your structure's registration.
             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
             * If you are using mixins, you can call the codec method with an invoker mixin instead of using reflection.
             */
            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                EldenCraft.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
            if(serverWorld.getChunkSource().getGenerator() instanceof FlatLevelSource &&
                    serverWorld.dimension().equals(Level.OVERWORLD)){
                return;
            }

            Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());

            tempMap.putIfAbsent(EldenStructures.DIVINE_TOWER.get(), StructureSettings.DEFAULTS.get(EldenStructures.DIVINE_TOWER.get()));
            tempMap.putIfAbsent(EldenStructures.LARGE_RUINS.get(), StructureSettings.DEFAULTS.get(EldenStructures.LARGE_RUINS.get()));
            tempMap.putIfAbsent(EldenStructures.MINOR_ERDTREE.get(), StructureSettings.DEFAULTS.get(EldenStructures.MINOR_ERDTREE.get()));


            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
        }
    }
}
