package net.bells.eldencraft.item;

import net.bells.eldencraft.EldenCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EldenItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EldenCraft.MOD_ID);


    public static void register(IEventBus thebus) {
        ITEMS.register(thebus);
    }
}
