package com.eon.sharkmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eon.sharkmod.init.ItemInit;
import com.eon.sharkmod.init.ModEntityTypes;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("eonshark")
public class SharkMod {
	
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "eonshark";
	public static SharkMod instance;

	public SharkMod() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		
		ItemInit.ITEMS.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
		// do something when the server starts
		LOGGER.info("HELLO from server starting");
	}
	
	public static class SharkItemGroup extends ItemGroup
	{
		public static final SharkItemGroup instance = new SharkItemGroup(ItemGroup.GROUPS.length, "eonsharktab");
		private SharkItemGroup(int index, String label) {
			super(index, label);
		}
		
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.SHARK_FIN.get());
		}
	}
}
