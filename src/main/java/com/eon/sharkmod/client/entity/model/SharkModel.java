package com.eon.sharkmod.client.entity.model;

import com.eon.sharkmod.entities.SharkEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class SharkModel<T extends SharkEntity> extends EntityModel<T> {
	private final ModelRenderer bone13;
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
		textureWidth = 16;
		textureHeight = 16;

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bone13, 0.0F, 1.5708F, 0.0F);
		

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone13.addChild(Body);
		Body.setTextureOffset(0, 0).addBox(6.467F, 0.7955F, 0.0881F, 1.0F, 1.0F, 1.0F, 5.0F, false);
		Body.setTextureOffset(0, 0).addBox(-2.533F, 0.2955F, 0.0881F, 0.0F, 1.0F, 1.0F, 4.0F, false);
		Body.setTextureOffset(0, 0).addBox(-9.533F, -0.2045F, 0.0881F, 0.0F, 1.0F, 1.0F, 3.0F, false);
		Body.setTextureOffset(0, 0).addBox(-14.533F, -0.7045F, 0.0881F, 0.0F, 1.0F, 1.0F, 2.0F, false);
		Body.setTextureOffset(0, 0).addBox(16.467F, 0.7955F, 0.0881F, 0.0F, 1.0F, 1.0F, 4.0F, false);
		Body.setTextureOffset(0, 0).addBox(-21.533F, -2.7045F, -0.9119F, 5.0F, 3.0F, 3.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(20.467F, -3.2045F, -3.9119F, 1.0F, 8.0F, 9.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(21.467F, -3.2045F, -3.9119F, 1.0F, 7.0F, 9.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(22.467F, -3.2045F, -3.9119F, 1.0F, 6.0F, 9.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(23.467F, -3.2045F, -3.4119F, 1.0F, 5.0F, 8.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(24.467F, -3.2045F, -2.9119F, 1.0F, 4.0F, 7.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(25.467F, -3.2045F, -1.9119F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		Body.setTextureOffset(0, 0).addBox(26.467F, -3.2045F, -0.9119F, 1.0F, 2.0F, 3.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-23.5F, -9.05F, -3.5F);
		Body.addChild(bone4);
		bone4.setTextureOffset(0, 0).addBox(2.967F, 7.2955F, -0.4119F, 2.0F, 1.0F, 3.0F, 0.0F, false);
		bone4.setTextureOffset(0, 0).addBox(2.967F, 7.2955F, 5.5881F, 2.0F, 1.0F, 3.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-32.0F, -5.45F, 0.0F);
		Body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3491F);
		bone.setTextureOffset(0, 0).addBox(4.8854F, 7.6927F, -4.9119F, 5.0F, 1.0F, 11.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(4.8854F, 7.6927F, -5.9119F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(4.8854F, 7.6927F, 6.0881F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-3.6419F, 1.2392F, -5.2F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
		bone2.setTextureOffset(0, 0).addBox(5.6473F, 6.4991F, -4.306F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		bone2.setTextureOffset(0, 0).addBox(3.6473F, 6.4991F, -5.306F, 3.0F, 1.0F, 4.0F, -1.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(6.9696F, -0.0338F, 11.3271F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.8727F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(5.4394F, 6.5329F, -1.6916F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		bone3.setTextureOffset(0, 0).addBox(3.4394F, 6.5329F, -0.6916F, 3.0F, 1.0F, 4.0F, -1.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-3.5F, -5.375F, 0.0F);
		Body.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.3491F);
		bone5.setTextureOffset(0, 0).addBox(6.6894F, 2.6138F, -0.9119F, 5.0F, 3.0F, 3.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-1.5425F, 0.7396F, -0.5F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.5236F);
		bone6.setTextureOffset(0, 0).addBox(5.625F, 5.2352F, 0.0881F, 5.0F, 3.0F, 2.0F, 0.0F, false);
		bone6.setTextureOffset(0, 0).addBox(6.625F, 3.2352F, 0.5881F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		bone6.setTextureOffset(0, 0).addBox(8.625F, 1.2352F, 0.5881F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-0.5F, -2.5F, -6.5F);
		bone13.addChild(bone9);
		

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone9.addChild(bone7);
		setRotationAngle(bone7, 0.3491F, 0.2618F, -0.0873F);
		bone7.setTextureOffset(0, 0).addBox(4.467F, 6.7955F, -1.4119F, 5.0F, 1.0F, 4.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-2.0F, 0.0F, -5.0F);
		bone9.addChild(bone8);
		setRotationAngle(bone8, 0.3491F, 0.2618F, -0.0873F);
		bone8.setTextureOffset(0, 0).addBox(5.0974F, 8.7875F, 1.5514F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		bone8.setTextureOffset(0, 0).addBox(5.0974F, 8.7875F, -0.4486F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(5.5F, 2.8F, 2.7F);
		bone13.addChild(bone10);
		setRotationAngle(bone10, 2.3562F, 0.0F, 0.0F);
		

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.3491F, 0.2618F, -0.0873F);
		bone11.setTextureOffset(0, 0).addBox(-0.2536F, 0.672F, -5.8089F, 5.0F, 1.0F, 4.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-2.0F, 0.0F, -5.0F);
		bone10.addChild(bone12);
		setRotationAngle(bone12, 0.3491F, 0.2618F, -0.0873F);
		bone12.setTextureOffset(0, 0).addBox(0.3768F, 2.664F, -2.8456F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		bone12.setTextureOffset(0, 0).addBox(0.3768F, 2.664F, -4.8456F, 2.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone13.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
