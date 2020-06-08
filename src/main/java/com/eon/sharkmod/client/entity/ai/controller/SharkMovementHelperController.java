package com.eon.sharkmod.client.entity.ai.controller;

import com.eon.sharkmod.SharkMod;
import com.eon.sharkmod.entities.SharkEntity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;

public class SharkMovementHelperController extends MovementController {
	private final SharkEntity shark;

	public SharkMovementHelperController(SharkEntity sharkIn) {
		super(sharkIn);
		this.shark = sharkIn;
	}

	public void tick() {
		// SHARK TEST CODE
		this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, 15, 10.0F);
		this.shark.renderYawOffset = this.shark.rotationYaw;
		this.shark.rotationYawHead = this.shark.rotationYaw;
		this.shark.rotationPitch = this.limitAngle(this.shark.rotationPitch, 15, 5.0F);
		this.shark.moveForward = 20;
		this.shark.moveVertical = 20;
		/*
		// DOLPHIN CODE
		if (this.shark.isInWater()) {
			this.shark.setMotion(this.shark.getMotion().add(0.0D, 0.005D, 0.0D));
		}

		if (this.action == MovementController.Action.MOVE_TO && !this.shark.getNavigator().noPath()) {
			double dPosX = this.posX - this.shark.getPosX();
			double dPosY = this.posY - this.shark.getPosY();
			double dPosZ = this.posZ - this.shark.getPosZ();
			double d3 = dPosX * dPosX + dPosY * dPosY + dPosZ * dPosZ;
			if (d3 < (double) 2.5000003E-7F) {
				this.mob.setMoveForward(0.0F);
			} else {
				// test values: dPosX = -30.06, dPosZ = -64.96, dPosY = -62.1

				// (radian angle * base radian calc = 63 degrees) - 90 degrees = -26 degrees
				float orientationZX = (float) (MathHelper.atan2(dPosZ, dPosX) * (double) (180F / (float) Math.PI))
						- 90.0F;
				this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, orientationZX, 10.0F);
				this.shark.renderYawOffset = this.shark.rotationYaw;
				this.shark.rotationYawHead = this.shark.rotationYaw;
				float movementSpeed = (float) (this.speed
						* this.shark.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());

				if (this.shark.isInWater()) {
					this.shark.setAIMoveSpeed(movementSpeed * 0.02F);

					// arctan(dPosY, hypotenuse) * base radian calc = -40.89 degrees
					float orientationVertical = -((float) (MathHelper.atan2(dPosY,
							(double) MathHelper.sqrt(dPosX * dPosX + dPosZ * dPosZ))
							* (double) (180F / (float) Math.PI)));
					orientationVertical = MathHelper.clamp(MathHelper.wrapDegrees(orientationVertical), -85.0F, 85.0F);

					// orientationVertical is still equal to -40.89 because it passed the clamp
					// check

					// tries to rotate shark towards rotation pitch, at max 5 degrees at a time
					this.shark.rotationPitch = this.limitAngle(this.shark.rotationPitch, orientationVertical, 5.0F);
					float f3 = MathHelper.cos(this.shark.rotationPitch * ((float) Math.PI / 180F));
					float f4 = MathHelper.sin(this.shark.rotationPitch * ((float) Math.PI / 180F));
					this.shark.moveForward = f3 * movementSpeed;
					this.shark.moveVertical = -f4 * movementSpeed;
				} else {
					this.shark.setAIMoveSpeed(movementSpeed * 0.1F);
				}
			}
			SharkMod.LOGGER.info("this.shark.rotationYaw: " + this.shark.rotationYaw + "this.shark.renderYawOffset: "
					+ this.shark.renderYawOffset + "this.shark.rotationYawHead: " + this.shark.rotationYawHead
					+ "this.shark.rotationPitch: " + this.shark.rotationPitch + "this.shark.moveForward: "
					+ this.shark.moveForward + "this.shark.moveVertical: " + this.shark.moveVertical);
		} else {
			this.shark.setAIMoveSpeed(0.0F);
			this.shark.setMoveStrafing(0.0F);
			this.shark.setMoveVertical(0.0F);
			this.shark.setMoveForward(0.0F);
		}*/
	}
}
