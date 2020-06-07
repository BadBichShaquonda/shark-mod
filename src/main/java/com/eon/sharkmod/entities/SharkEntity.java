package com.eon.sharkmod.entities;

import com.eon.sharkmod.SharkMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SharkEntity extends WaterMobEntity {


	private static final DataParameter<Integer> MOISTNESS = EntityDataManager.createKey(DolphinEntity.class,
			DataSerializers.VARINT);

	public SharkEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new SharkEntity.MoveHelperController(this);
		this.lookController = new LookController(this);
	}

	/**
	 * Not sure what this does!
	 */
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.rotationPitch = 0.0F;
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	/**
	 * I think the movement controllers should be looked at before the goals
	 */
	protected void registerGoals() {
		//this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1.0D, 5));
	}

	/**
	 * Moistness - Mob takes damage when moistness falls below a certain threshold
	 * Functions properly with tick() method code
	 */
	public int getMoistness() {
		return this.dataManager.get(MOISTNESS);
	}

	public void setMoistness(int p_211137_1_) {
		this.dataManager.set(MOISTNESS, p_211137_1_);
	}

	/**
	 * A sad attempt at getting my shark to do something remotely interesting (from
	 * the dolphin class)
	 */
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),
				(float) ((int) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));
		if (flag) {
			this.applyEnchantments(this, entityIn);
			this.playSound(SoundEvents.ENTITY_DOLPHIN_ATTACK, 1.0F, 1.0F);
		}

		return flag;
	}

	/**
	 * Not sure if this is doing anything, because the registerAttributes crashes
	 * anyway when setting an attack damage
	 */
	@Override
	public boolean canAttack(EntityType<?> typeIn) {
		return true;
	}

	/**
	 * Tune these later on. Too tanky atm
	 * 
	 * What is follow range? Is that view-entity range? Can be very useful
	 */
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
		// this.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(2.0D);
		// this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 1.5F);
	}

	/**
	 * I have this method because I'm copying the dolphin class for the moistness
	 */
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOISTNESS, 2400);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		this.getLookController().setLookPosition(32, this.getPosYEye(), 70);
		SharkMod.LOGGER.info("this.posX: " + this.getLookController().getLookPosX() + ", this.posY: " + this.getLookController().getLookPosY() 
				+ ", this.posZ: " + this.getLookController().getLookPosZ() + ", is looking: " + this.getLookController().getIsLooking());
		/*
		if (!this.isAIDisabled()) {
			if (this.isInWaterRainOrBubbleColumn()) {
				this.setMoistness(2400);
			} else {
				this.setMoistness(this.getMoistness() - 1);
				if (this.getMoistness() <= 0) {
					this.attackEntityFrom(DamageSource.DRYOUT, 1.0F);
				}

				if (this.onGround) {
					this.setMotion(this.getMotion().add((double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D,
							(double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F)));
					this.rotationYaw = this.rand.nextFloat() * 360.0F;
					this.onGround = false;
					this.isAirBorne = true;
				}
			}

			if (this.world.isRemote && this.isInWater() && this.getMotion().lengthSquared() > 0.03D) {
				Vec3d vec3d = this.getLook(0.0F);
				float orientationZX = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)) * 0.3F;
				float movementSpeed = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)) * 0.3F;
				float orientationVertical = 1.2F - this.rand.nextFloat() * 0.7F;

				for (int i = 0; i < 2; ++i) {
					this.world.addParticle(ParticleTypes.DOLPHIN, this.getPosX() - vec3d.x * (double) orientationVertical + (double) orientationZX,
							this.getPosY() - vec3d.y, this.getPosZ() - vec3d.z * (double) orientationVertical + (double) movementSpeed, 0.0D, 0.0D,
							0.0D);
					this.world.addParticle(ParticleTypes.DOLPHIN, this.getPosX() - vec3d.x * (double) orientationVertical - (double) orientationZX,
							this.getPosY() - vec3d.y, this.getPosZ() - vec3d.z * (double) orientationVertical - (double) movementSpeed, 0.0D, 0.0D,
							0.0D);
				}
			}

		}*/
	}

	static class MoveHelperController extends MovementController {
		private final SharkEntity shark;

		public MoveHelperController(SharkEntity sharkIn) {
			super(sharkIn);
			this.shark = sharkIn;
		}

		public void tick() {
//			SharkMod.LOGGER.info("this.posX: " + this.posX + ", this.posY: " + this.posY + ", this.posZ: " + this.posZ);
//			SharkMod.LOGGER.info("this.shark.getPosX(): " + this.shark.getPosX() + ", this.shark.getPosY(): " + this.shark.getPosY() + ", this.shark.getPosZ(): " + this.shark.getPosZ());
//			SharkMod.LOGGER.info("dPosX: " + dPosX + ", dPosY: " + dPosY + ", dPosZ: " + dPosZ + ", d3: " + d3);
			
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
					//test values: dPosX = -30.06, dPosZ = -64.96, dPosY = -62.1
					
					//(radian angle * base radian calc = 63 degrees) - 90 degrees = -26 degrees
					float orientationZX = (float) (MathHelper.atan2(dPosZ, dPosX) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, orientationZX, 10.0F);
					this.shark.renderYawOffset = this.shark.rotationYaw;
					this.shark.rotationYawHead = this.shark.rotationYaw;
					float movementSpeed = (float) (this.speed * this.shark.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					
					if (this.shark.isInWater()) {
						this.shark.setAIMoveSpeed(movementSpeed * 0.02F);
						
						//arctan(dPosY, hypotenuse) * base radian calc = -40.89 degrees
						float orientationVertical = -((float) (MathHelper.atan2(dPosY, (double) MathHelper.sqrt(dPosX * dPosX + dPosZ * dPosZ)) * (double) (180F / (float) Math.PI)));
						orientationVertical = MathHelper.clamp(MathHelper.wrapDegrees(orientationVertical), -85.0F, 85.0F);
						
						//orientationVertical is still equal to -40.89 because it passed the clamp check
						
						//tries to rotate shark towards rotation pitch, at max 5 degrees at a time
						this.shark.rotationPitch = this.limitAngle(this.shark.rotationPitch, orientationVertical, 5.0F);
						float f3 = MathHelper.cos(this.shark.rotationPitch * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.shark.rotationPitch * ((float) Math.PI / 180F));
						this.shark.moveForward = f3 * movementSpeed;
						this.shark.moveVertical = -f4 * movementSpeed;
					} else {
						this.shark.setAIMoveSpeed(movementSpeed * 0.1F);
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
