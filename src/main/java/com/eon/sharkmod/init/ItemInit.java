package com.eon.sharkmod.init;

import com.eon.sharkmod.SharkMod;
import com.eon.sharkmod.SharkMod.SharkItemGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder; 

@Mod.EventBusSubscriber(modid = SharkMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SharkMod.MOD_ID)
public class ItemInit {
	public static final Item shark_fin = null;
	public static final Item raw_shark_meat = null;
	public static final Item cooked_shark_meat = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(SharkItemGroup.instance))
				.setRegistryName("shark_fin"));
		
		event.getRegistry().register(new Item(new Item.Properties().group(SharkItemGroup.instance)
				.food(new Food.Builder().hunger(2).saturation(1F).effect(() -> new EffectInstance(Effects.HUNGER, 200, 0), 0.85F).build()))
				.setRegistryName("raw_shark_meat"));
		
		event.getRegistry().register(new Item(new Item.Properties().group(SharkItemGroup.instance)
				.food(new Food.Builder().hunger(8).saturation(4F).build()))
				.setRegistryName("cooked_shark_meat"));
	}
}
