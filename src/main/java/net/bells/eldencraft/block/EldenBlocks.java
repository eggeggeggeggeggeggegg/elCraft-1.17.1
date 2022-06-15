package net.bells.eldencraft.block;

import net.bells.eldencraft.EldenCraft;
import net.bells.eldencraft.gen.trees.LimgraveOakGrower;
import net.bells.eldencraft.item.EldenItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class EldenBlocks {

    //RegistryObject req .get()

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EldenCraft.MOD_ID);

    public static final RegistryObject<Block> STRUCTURE_STONE = registerBlock("structure_stone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F,6.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> ERD_TREE_LEAVES = registerBlock("erd_tree_leaves",
            () -> new Block(BlockBehaviour.Properties.of(Material.LEAVES).requiresCorrectToolForDrops().strength(1.5F,6.0F).lightLevel(BlockState -> 32)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> LIMGRAVE_OAK_SAPLING = registerBlock("limgrave_oak_sapling",
            () -> new SaplingBlock(new LimgraveOakGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
//new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return EldenItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }



    public static void register(IEventBus thebus) {
        BLOCKS.register(thebus);
    }
}
