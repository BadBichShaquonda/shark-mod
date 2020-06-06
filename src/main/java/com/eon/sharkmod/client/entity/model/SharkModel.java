package com.eon.sharkmod.client.entity.model;

import com.eon.sharkmod.entities.SharkEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SharkModel<T extends SharkEntity> extends EntityModel<T> {
	private final ModelRenderer Body;
	private final ModelRenderer bone4;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone9;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;

	public SharkModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(0, 0).addBox(-0.5F, -6.5F, -0.5F, 1.0F, 1.0F, 1.0F, 5.0F, false);
		Body.setTextureOffset(0, 0).addBox(-9.5F, -7.0F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, false);
		Body.setTextureOffset(0, 0).addBox(-16.5F, -7.5F, -0.5F, 0.0F, 1.0F, 1.0F, 3.0F, false);
		Body.setTextureOffset(0, 0).addBox(-21.5F, -8.0F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, false);
		Body.setTextureOffset(0, 0).addBox(9.5F, -6.5F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, false);
		Body.setTextureOffset(0, 0).addBox(-28.5F, -10.0F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(13.5F, -10.5F, -4.5F, 1.0F, 8.0F, 9.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(14.5F, -10.5F, -4.5F, 1.0F, 7.0F, 9.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(15.5F, -10.5F, -4.5F, 1.0F, 6.0F, 9.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(16.5F, -10.5F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(17.5F, -10.5F, -3.5F, 1.0F, 4.0F, 7.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(18.5F, -10.5F, -2.5F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(19.5F, -10.5F, -1.5F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-23.5F, -9.05F, -3.5F);
		Body.addChild(bone4);
		bone4.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -1.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		bone4.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, 5.0F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-30.0F, -8.7F, 0.0F);
		Body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3491F);
		bone.setTextureOffset(0, 0).addBox(-2.0816F, 0.3972F, -5.5F, 5.0F, 1.0F, 11.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(-2.0816F, 0.3972F, -6.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(-2.0816F, 0.3972F, 5.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-3.6419F, 1.2392F, -5.2F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
		bone2.setTextureOffset(0, 0).addBox(-1.0276F, -0.7626F, -1.7203F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(0, 0).addBox(-3.0276F, -0.7626F, -2.7203F, 3.0F, 1.0F, 4.0F, -1.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(4.7262F, 0.0F, 9.0631F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.8727F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(-1.5276F, -0.7626F, -2.2797F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		bone3.setTextureOffset(0, 0).addBox(-3.5276F, -0.7626F, -1.2797F, 3.0F, 1.0F, 4.0F, -1.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(1.5F, -13.0F, 0.0F);
		Body.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.3491F);
		bone5.setTextureOffset(0, 0).addBox(-2.842F, -0.5603F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-1.5425F, 0.7396F, -0.5F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
		bone6.setTextureOffset(0, 0).addBox(-1.342F, -2.0603F, -0.5F, 5.0F, 3.0F, 2.0F, 0.0F, false);
		bone6.setTextureOffset(0, 0).addBox(-0.342F, -4.0603F, 0.0F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		bone6.setTextureOffset(0, 0).addBox(1.658F, -6.0603F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-0.5F, 21.5F, -6.5F);
		

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone9.addChild(bone7);
		setRotationAngle(bone7, 0.3491F, 0.2618F, -0.0873F);
		bone7.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -2.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-2.0F, 0.0F, -5.0F);
		bone9.addChild(bone8);
		setRotationAngle(bone8, 0.3491F, 0.2618F, -0.0873F);
		bone8.setTextureOffset(0, 0).addBox(-1.8696F, 1.492F, 0.9633F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		bone8.setTextureOffset(0, 0).addBox(-1.8696F, 1.492F, -1.0367F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-0.5F, 21.5F, 6.5F);
		setRotationAngle(bone10, 2.3562F, 0.0F, 0.0F);
		

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.3491F, 0.2618F, -0.0873F);
		bone11.setTextureOffset(0, 0).addBox(-2.5F, -0.5F, -2.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-2.0F, 0.0F, -5.0F);
		bone10.addChild(bone12);
		setRotationAngle(bone12, 0.3491F, 0.2618F, -0.0873F);
		bone12.setTextureOffset(0, 0).addBox(-1.8696F, 1.492F, 0.9633F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		bone12.setTextureOffset(0, 0).addBox(-1.8696F, 1.492F, -1.0367F, 2.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay);
		bone9.render(matrixStack, buffer, packedLight, packedOverlay);
		bone10.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
