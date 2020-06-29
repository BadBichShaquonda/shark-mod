package com.eon.sharkmod.client.entity.ai.goal;

import java.util.EnumSet;

import com.eon.sharkmod.entities.SharkEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class CirclePreyGoal extends Goal {
	protected final SharkEntity attacker;
	protected int attackTick;
	private final double speedTowardsTarget;
	private final boolean longMemory;
	private Path path;
	private int delayCounter;
	private double targetX;
	private double targetY;
	private double targetZ;
	protected final int attackInterval = 20;
	private long field_220720_k;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;

	public CirclePreyGoal(SharkEntity creature, double speedIn, boolean useLongMemory) {
		this.attacker = creature;
		this.speedTowardsTarget = speedIn;
		this.longMemory = useLongMemory;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */
	public boolean shouldExecute() {
		
		
		long i = this.attacker.world.getGameTime();
		if (i - this.field_220720_k < 20L) {
			return false;
		} else {
			this.field_220720_k = i;
			LivingEntity livingentity = this.attacker.getAttackTarget();
			if (livingentity == null) {
				return false;
			} else if (!livingentity.isAlive()) {
				return false;
			} else {
				if (canPenalize) {
					if (--this.delayCounter <= 0) {
						this.path = this.attacker.getNavigator().getPathToEntity(livingentity, 0);
						this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
						return this.path != null;
					} else {
						return true;
					}
				}
				this.path = this.attacker.getNavigator().getPathToEntity(livingentity, 0);
				if (this.path != null) {
					return true;
				} else {
					return this.getAttackReachSqr(livingentity) >= this.attacker.getDistanceSq(livingentity.getPosX(),
							livingentity.getPosY(), livingentity.getPosZ());
				}
			}
		}
	}
	
	/**
	 * Calculates the distance (hypotenuse) between the shark and its target.
	 * 
	 * @return the distance as a double
	 */
	private double getXZDistanceToTarget() {
		double xDistance = attacker.getPosX() - attacker.getAttackTarget().getPosX();
		double zDistance = attacker.getPosZ() - attacker.getAttackTarget().getPosZ();
		return Math.hypot(xDistance, zDistance);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		if (livingentity == null) {
			return false;
		} else if (!livingentity.isAlive()) {
			return false;
		} else if (!this.longMemory) {
			return !this.attacker.getNavigator().noPath();
		} else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(livingentity))) {
			return false;
		} else {
			return !(livingentity instanceof PlayerEntity)
					|| !livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative();
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
		this.attacker.setAggroed(true);
		this.delayCounter = 0;
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void resetTask() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		if (!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
			this.attacker.setAttackTarget((LivingEntity) null);
		}

		this.attacker.setAggroed(false);
		this.attacker.getNavigator().clearPath();
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		this.attacker.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
		double d0 = this.attacker.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
		--this.delayCounter;
		if ((this.longMemory || this.attacker.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0
				&& (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D
						|| livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D
						|| this.attacker.getRNG().nextFloat() < 0.05F)) {
			this.targetX = livingentity.getPosX();
			this.targetY = livingentity.getPosY();
			this.targetZ = livingentity.getPosZ();
			this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
			if (this.canPenalize) {
				this.delayCounter += failedPathFindingPenalty;
				if (this.attacker.getNavigator().getPath() != null) {
					PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
					if (finalPathPoint != null
							&& livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
						failedPathFindingPenalty = 0;
					else
						failedPathFindingPenalty += 10;
				} else {
					failedPathFindingPenalty += 10;
				}
			}
			if (d0 > 1024.0D) {
				this.delayCounter += 10;
			} else if (d0 > 256.0D) {
				this.delayCounter += 5;
			}

			if (!this.attacker.getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
				this.delayCounter += 15;
			}
		}

		this.attackTick = Math.max(this.attackTick - 1, 0);
		this.checkAndPerformAttack(livingentity, d0);
	}

	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= d0 && this.attackTick <= 0) {
			if(enemy.isPassenger() && enemy.getRidingEntity().getClass() == BoatEntity.class) {
				BoatEntity boat = (BoatEntity) enemy.getRidingEntity();
				dropBrokenBoatPiecesAndBreak(boat);
			} else {
				this.attackTick = 20;
				this.attacker.swingArm(Hand.MAIN_HAND);
				this.attacker.attackEntityAsMob(enemy);				
			}
		}
	}

	/**
	 * Takes a boat, drops 3 wood planks and 2 sticks and destroys it.
	 * 
	 * @param boat
	 */
	protected void dropBrokenBoatPiecesAndBreak(BoatEntity boat) {
		switch (boat.getBoatType()) {
		case OAK:
		default:
			boat.entityDropItem(new ItemStack(Items.OAK_PLANKS, 3));
			break;
		case SPRUCE:
			boat.entityDropItem(new ItemStack(Items.SPRUCE_PLANKS, 3));
			break;
		case BIRCH:
			boat.entityDropItem(new ItemStack(Items.BIRCH_PLANKS, 3));
			break;
		case JUNGLE:
			boat.entityDropItem(new ItemStack(Items.JUNGLE_PLANKS, 3));
			break;
		case ACACIA:
			boat.entityDropItem(new ItemStack(Items.ACACIA_PLANKS, 3));
			break;
		case DARK_OAK:
			boat.entityDropItem(new ItemStack(Items.DARK_OAK_PLANKS, 3));
			break;
		}
		boat.entityDropItem(new ItemStack(Items.STICK, 2));
		boat.remove();
	}

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return (double) (this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth());
	}
}
