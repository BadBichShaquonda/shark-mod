package com.eon.sharkmod.entities;

import java.util.Random;

import javax.annotation.Nullable;

import com.eon.sharkmod.client.entity.ai.controller.SharkMovementHelperController;
import com.eon.sharkmod.client.entity.ai.goal.SlayFishGoal;
import com.eon.sharkmod.util.helper.RandomSwimmingGoal;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;

public class SharkEntity extends WaterMobEntity {

	private int tickTimer;

	private static final DataParameter<Integer> MOISTNESS = EntityDataManager.createKey(DolphinEntity.class,
			DataSerializers.VARINT);

	public SharkEntity(EntityType<? extends WaterMobEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new SharkMovementHelperController(this);
		this.tickTimer = 0;
		this.recalculateSize();
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
		this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1.0D, 5));
		this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(0, new SlayFishGoal<>(this));
	}

	/**
	 * Sets the active target the Task system uses for tracking
	 */
	public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
		super.setAttackTarget(entitylivingbaseIn);
	}

	/**
	 * Moistness - Mob takes damage when moistness falls below a certain threshold
	 * Functions properly with tick() method code
	 */
	public int getMoistness() {
		return this.dataManager.get(MOISTNESS);
	}

	public void setMoistness(int moistness) {
		this.dataManager.set(MOISTNESS, moistness);
	}

	/**
	 * A sad attempt at getting my shark to do something remotely interesting (from
	 * the dolphin class)
	 */
	public boolean attackEntityAsMob(PlayerEntity entityIn) {
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
	 * Leads?
	 */
	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return true;
	}

	/**
	 * Tune these later on. Too tanky atm
	 * 
	 * What is follow range? Is that view-entity range? Can be very useful I think
	 * it's following-breeding-item range
	 */
	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		// Register new attributes (as WaterMobEntities do not have attack attributes)
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED);

		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(2.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	/**
	 * I have this method because I'm copying the dolphin class for the moistness
	 */
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOISTNESS, 2400);
	}

	// working code:
	// attack someone
	// attackEntityAsMob(this.world.getClosestPlayer(this, 50));
	// SharkMod.LOGGER.info("Closest player is: " +
	// this.world.getClosestPlayer(this, 50).getName());

	/**
	 * Called to update the entity's position/logic.
	 */
	public void tick() {
		super.tick();
		this.tickTimer++;
		if (this.tickTimer == 40) {
			// this.moveController.setMoveTo(32, this.getEyeHeight(), 70, 0.5D);
			// this.move(MoverType.SELF, new Vec3d(32, this.getEyeHeight(), 70));

			//
			// tickTimer = 0;
		}

		// this.lookController.setLookPosition(32, this.getPosYEye(), 70);

		/*
		 * double d0 = (Math.PI * 2D) * getRNG().nextDouble(); double lookX =
		 * Math.cos(d0); double lookZ = Math.sin(d0); if (this.tickTimer == 40) {
		 * this.lookController.setLookPosition(getPosX() + lookX, getPosYEye(),
		 * getPosZ() + lookZ); tickTimer = 0; }
		 */
		// SharkMod.LOGGER.info("this.posX: " + this.getLookController().getLookPosX() +
		// ", this.posY: " + this.getLookController().getLookPosY() + ", this.posZ: " +
		// this.getLookController().getLookPosZ() + ", is looking: " +
		// this.getLookController().getIsLooking());

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

	public void travel(Vec3d positionIn) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(this.getAIMoveSpeed(), positionIn);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(positionIn);
		}
	}

	/*
	 * protected PathNavigator createNavigator(World worldIn) { return new
	 * SwimmerPathNavigator(this, worldIn); }
	 */

	public int getVerticalFaceSpeed() {
		return 1;
	}

	public int getHorizontalFaceSpeed() {
		return 1;
	}

	public static boolean func_223364_b(EntityType<SharkEntity> sharkEntity, IWorld world, SpawnReason reason,
			BlockPos somePos, Random rand) {
		// If somePos in between 45 and sea level AND
		// If somePos not in ocean or deep ocean AND
		// If somePos is tagged water
		return somePos.getY() > 45 && somePos.getY() < world.getSeaLevel()
				&& (world.getBiome(somePos) != Biomes.OCEAN || world.getBiome(somePos) != Biomes.DEEP_OCEAN)
				&& world.getFluidState(somePos).isTagged(FluidTags.WATER);
	}

}
