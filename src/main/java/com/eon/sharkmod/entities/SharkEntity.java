package com.eon.sharkmod.entities;

import com.eon.sharkmod.SharkMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class SharkEntity extends WaterMobEntity {

	private static final DataParameter<Integer> MOISTNESS = EntityDataManager.createKey(DolphinEntity.class,
			DataSerializers.VARINT);

	public SharkEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new SharkEntity.MoveHelperController(this);
		// this.lookController = new DolphinLookController(this, 10);
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
		// this.goalSelector.addGoal(1, new RandomSwimmingGoal(this, 1.0D, 10));
		// this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		/*
		 * this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		 * this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double) 1.2F, true));
		 * 
		 * //Spider method - not working this.targetSelector.addGoal(1, new
		 * HurtByTargetGoal(this)); //Drowned attackable target goal hm
		 * this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this,
		 * PlayerEntity.class, true, false)); this.targetSelector.addGoal(1, new
		 * NearestAttackableTargetGoal<>(this, TropicalFishEntity.class, true, false));
		 */
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
		// this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
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
				float f = MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F)) * 0.3F;
				float f1 = MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)) * 0.3F;
				float f2 = 1.2F - this.rand.nextFloat() * 0.7F;

				for (int i = 0; i < 2; ++i) {
					this.world.addParticle(ParticleTypes.DOLPHIN, this.getPosX() - vec3d.x * (double) f2 + (double) f,
							this.getPosY() - vec3d.y, this.getPosZ() - vec3d.z * (double) f2 + (double) f1, 0.0D, 0.0D,
							0.0D);
					this.world.addParticle(ParticleTypes.DOLPHIN, this.getPosX() - vec3d.x * (double) f2 - (double) f,
							this.getPosY() - vec3d.y, this.getPosZ() - vec3d.z * (double) f2 - (double) f1, 0.0D, 0.0D,
							0.0D);
				}
			}

		}
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
				double dPosX = this.posX - this.shark.getPosX();
				double dPosY = this.posY - this.shark.getPosY();
				double dPosZ = this.posZ - this.shark.getPosZ();
				double d3 = dPosX * dPosX + dPosY * dPosY + dPosZ * dPosZ;
				SharkMod.LOGGER.info("dPosX: " + dPosX + ", dPosY: " + dPosY + ", dPosZ: " + dPosZ + ", d3: " + d3);
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setMoveForward(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(dPosZ, dPosX) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, f, 10.0F);
					this.shark.renderYawOffset = this.shark.rotationYaw;
					this.shark.rotationYawHead = this.shark.rotationYaw;
					float f1 = (float) (this.speed
							* this.shark.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					if (this.shark.isInWater()) {
						this.shark.setAIMoveSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(dPosY,
								(double) MathHelper.sqrt(dPosX * dPosX + dPosZ * dPosZ))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.shark.rotationPitch = this.limitAngle(this.shark.rotationPitch, f2, 5.0F);
						float f3 = MathHelper.cos(this.shark.rotationPitch * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.shark.rotationPitch * ((float) Math.PI / 180F));
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
