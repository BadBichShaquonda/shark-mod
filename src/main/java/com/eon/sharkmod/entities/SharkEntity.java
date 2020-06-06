package com.eon.sharkmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SharkEntity extends WaterMobEntity {

	public SharkEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.5D, 90));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double) 1.2F, true));
		
		//Spider method - not working
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		//Drowned attackable target goal hm
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, TropicalFishEntity.class, true, false));
	}

	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return true;
	}
	
	static class MoveHelperController extends MovementController {
	      private final SharkEntity shark;

	      public MoveHelperController(SharkEntity sharkIn) {
	         super(sharkIn);
	         this.shark = sharkIn;
	      }

	      public void tick() {
	         if (this.shark.isInWater()) {
	            this.shark.setMotion(this.shark.getMotion().add(0.0D, 0.005D, 0.0D));
	         }

	         if (this.action == MovementController.Action.MOVE_TO && !this.shark.getNavigator().noPath()) {
	            double d0 = this.posX - this.shark.getPosX();
	            double d1 = this.posY - this.shark.getPosY();
	            double d2 = this.posZ - this.shark.getPosZ();
	            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
	            if (d3 < (double)2.5000003E-7F) {
	               this.mob.setMoveForward(0.0F);
	            } else {
	               float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
	               this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, f, 10.0F);
	               this.shark.renderYawOffset = this.shark.rotationYaw;
	               this.shark.rotationYawHead = this.shark.rotationYaw;
	               float f1 = (float)(this.speed * this.shark.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
	               if (this.shark.isInWater()) {
	                  this.shark.setAIMoveSpeed(f1 * 0.02F);
	                  float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
	                  f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
	                  this.shark.rotationPitch = this.limitAngle(this.shark.rotationPitch, f2, 5.0F);
	                  float f3 = MathHelper.cos(this.shark.rotationPitch * ((float)Math.PI / 180F));
	                  float f4 = MathHelper.sin(this.shark.rotationPitch * ((float)Math.PI / 180F));
	                  this.shark.moveForward = f3 * f1;
	                  this.shark.moveVertical = -f4 * f1;
	               } else {
	                  this.shark.setAIMoveSpeed(f1 * 0.1F);
	               }

	            }
	         } else {
	            this.shark.setAIMoveSpeed(0.0F);
	            this.shark.setMoveStrafing(0.0F);
	            this.shark.setMoveVertical(0.0F);
	            this.shark.setMoveForward(0.0F);
	         }
	      }
	   }
}
