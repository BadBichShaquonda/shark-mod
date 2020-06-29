package com.eon.sharkmod.client.entity.ai.goal;

import java.util.EnumSet;
import java.util.Random;

import com.eon.sharkmod.entities.SharkEntity;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.math.BlockPos;

public class LurkRandomlyGoal extends Goal {
	protected final SharkEntity shark;
	protected final Random random;
	protected double x;
	protected double y;
	protected double z;
	protected final double speed;
	protected boolean mustUpdate;

	/**
	 * A LurkDirection speaks on the X and Z positions of the shark.
	 * 
	 * If LurkX, only x will change while z does not. If LurkZ, only z will change
	 * while x does not. If LurkDiagonal, both x and z will change equally.
	 */
	enum LurkDirection {
		LurkX, LurkZ, LurkDiagonal;

		/**
		 * Chance of getting LurkX and LurkZ are 20% each, while chance of getting
		 * LurkDiagonal is 60%.
		 * 
		 * @return a semi random LurkDirection
		 */
		public static LurkDirection getSemiRandomLurkDirection() {
			int chance = new Random().nextInt(100) + 1;

			if (chance < 40) {
				return LurkDirection.LurkDiagonal;
			} else if (chance < 20) {
				return LurkDirection.LurkX;
			} else {
				return LurkDirection.LurkZ;
			}
		}
	}

	public LurkRandomlyGoal(SharkEntity sharkIn, double speedIn) {
		this.shark = sharkIn;
		this.random = sharkIn.getRNG();
		this.speed = speedIn;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	/**
	 * Returns whether execution should begin. You can also read and cache any state
	 * necessary for execution in this method as well.
	 */
	public boolean shouldExecute() {
		if (this.shark.isBeingRidden()) {
			return false;
		} else {
			if (!this.mustUpdate) {
				if (this.shark.getIdleTime() >= 100) {
					return false;
				}
			}

			BlockPos pos = this.getWaterCheckedSemiRandomPos();
			if (pos == null) {
				return false;
			} else {
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
				this.mustUpdate = false;
				return true;
			}
		}
	}

	/**
	 * Ensures that the position received from {@link #getSemiRandomPos()} is ok in
	 * water.
	 * 
	 * @return a (hopefully) valid position to path to.
	 */
	protected BlockPos getWaterCheckedSemiRandomPos() {
		BlockPos pos = this.getSemiRandomPos();

//		for (int i = 0; pos != null && !this.shark.world.getBlockState(new BlockPos(pos))
//				.allowsMovement(this.shark.world, new BlockPos(pos), PathType.WATER)
//				&& i++ < 10; pos = this.getSemiRandomPos()) {
//			;
//		}

		return pos;
	}

	/**
	 * Looks for a new position for the shark to move to from the given coordinates,
	 * while keeping a similar y value AND following a LurkDirection, determined
	 * randomly.
	 */
	protected BlockPos getSemiRandomPos() {
		// X and Z calc
		LurkDirection lurkDirection = LurkDirection.getSemiRandomLurkDirection();
		int lurkLength = random.nextInt(30 + 1) + 15;

		// change to given block pos if needed
		double x = this.shark.getPosX();
		double z = this.shark.getPosZ();
		int potentiallyNegativeMultiplier = random.nextInt(2) == 0 ? -1 : 1;

		switch (lurkDirection) {
		case LurkX:
			x += (lurkLength * potentiallyNegativeMultiplier);
			break;
		case LurkZ:
			z += (lurkLength * potentiallyNegativeMultiplier);
			break;
		case LurkDiagonal:
			x += (lurkLength * potentiallyNegativeMultiplier);
			potentiallyNegativeMultiplier = random.nextInt(2) == 0 ? -1 : 1;
			z += (lurkLength * potentiallyNegativeMultiplier);
			break;
		}

		// Y calc
		potentiallyNegativeMultiplier = random.nextInt(2) == 0 ? -1 : 1;
		int similarYDiff = random.nextInt(6) * potentiallyNegativeMultiplier;
		double seaLevel = this.shark.world.getSeaLevel();
		double y = this.shark.getPosY();

		for (int i = 0; y + similarYDiff > seaLevel && i < 10; i++) {
			potentiallyNegativeMultiplier = random.nextInt(2) == 0 ? -1 : 1;
			similarYDiff = random.nextInt(6) * potentiallyNegativeMultiplier;
		}

		y += similarYDiff;

		return new BlockPos(x, y, z);
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing.
	 */
	public boolean shouldContinueExecuting() {
		return !this.shark.getNavigator().noPath() && !this.shark.isBeingRidden();
	}

	/**
	 * Execute a one shot task or start executing a continuous task.
	 */
	public void startExecuting() {
		this.shark.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one.
	 */
	public void resetTask() {
		this.shark.getNavigator().clearPath();
		super.resetTask();
	}

	/**
	 * Makes task to bypass chance.
	 */
	public void makeUpdate() {
		this.mustUpdate = true;
	}
}
