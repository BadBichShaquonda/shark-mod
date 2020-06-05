package com.eon.sharkmod.client.entity.render;

import com.eon.sharkmod.SharkMod;
import com.eon.sharkmod.client.entity.model.SharkModel;
import com.eon.sharkmod.entities.Shark;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SharkRender extends MobRenderer<Shark, SharkModel<Shark>> {

	protected static final ResourceLocation TEXTURE = new ResourceLocation(SharkMod.MOD_ID, "textures/entity/shark.png");
	
	public SharkRender(EntityRendererManager renderManagerIn) {
		//0.5F is the shadow size
		super(renderManagerIn, new SharkModel<Shark>(), 0.5F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(Shark entity) {
		return TEXTURE;
	}
}
