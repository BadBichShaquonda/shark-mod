package com.eon.sharkmod.init;

import com.eon.sharkmod.SharkMod;
import com.eon.sharkmod.SharkMod.SharkItemGroup;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, SharkMod.MOD_ID);
	
	//SIMPLE ITEMS
	public static final RegistryObject<Item> SHARK_FIN = ITEMS.register("shark_fin", () -> 
		new Item(new Item.Properties().group(SharkItemGroup.instance)));
	
	public static final RegistryObject<Item> SHARK_TOOTH = ITEMS.register("shark_tooth", () -> 
		new Item(new Item.Properties().group(SharkItemGroup.instance)));
	
	//FOOD
	public static final RegistryObject<Item> RAW_SHARK_MEAT = ITEMS.register("raw_shark_meat", () -> 
		new Item(new Item.Properties().group(SharkItemGroup.instance)
				.food(new Food.Builder().hunger(3).saturation(1F).effect(() -> new EffectInstance(Effects.HUNGER, 200, 0), 0.85F).build())));
	
	public static final RegistryObject<Item> COOKED_SHARK_MEAT = ITEMS.register("cooked_shark_meat", () -> 
		new Item(new Item.Properties().group(SharkItemGroup.instance)
				.food(new Food.Builder().hunger(12).saturation(4F).build())));
	
	public static final RegistryObject<Item> SHARK_FIN_SOUP = ITEMS.register("shark_fin_soup", () -> 
		new Item(new Item.Properties().group(SharkItemGroup.instance)
				.food(new Food.Builder().hunger(10).saturation(5F).build())));
}
