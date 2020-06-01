package com.eon.sharkmod.init;

import com.eon.sharkmod.SharkMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder; 

@Mod.EventBusSubscriber(modid = SharkMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SharkMod.MOD_ID)
public class ItemInit {
	public static final Item shark_fin = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("shark_fin"));
	}
}
