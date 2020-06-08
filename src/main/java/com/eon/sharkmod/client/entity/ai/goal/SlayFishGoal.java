package com.eon.sharkmod.client.entity.ai.goal;

import com.eon.sharkmod.entities.SharkEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.world.World;

public class SlayFishGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
	private final SharkEntity shark;
	private final World world;

	public SlayFishGoal(SharkEntity sharkIn) {
		super(sharkIn, (Class<T>) SalmonEntity.class, 10, true, false, null);
		this.shark = sharkIn;
		this.world = sharkIn.world;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		return this.targetEntitySelector != null
				? this.targetEntitySelector.canTarget(this.goalOwner, this.nearestTarget)
				: super.shouldContinueExecuting();
	}
}
