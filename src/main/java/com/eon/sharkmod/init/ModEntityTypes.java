package com.eon.sharkmod.init;

import com.eon.sharkmod.SharkMod;
import com.eon.sharkmod.entities.SharkEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	
	//DEFERRED REGISTER
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,SharkMod.MOD_ID);

	public static final RegistryObject<EntityType<SharkEntity>> SHARK = ENTITY_TYPES.register("shark",
			() -> EntityType.Builder.<SharkEntity>create(SharkEntity::new, EntityClassification.WATER_CREATURE).size(0.9F, 1.3F)
					.build(new ResourceLocation(SharkMod.MOD_ID, "shark").toString()));
}
