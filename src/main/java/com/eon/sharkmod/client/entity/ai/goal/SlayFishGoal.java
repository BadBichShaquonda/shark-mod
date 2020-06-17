package com.eon.sharkmod.client.entity.ai.goal;

import java.util.function.Predicate;

import com.eon.sharkmod.entities.SharkEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.world.World;

public class SlayFishGoal extends NearestAttackableTargetGoal<LivingEntity> {
	private final SharkEntity shark;
	
	public SlayFishGoal(MobEntity goalOwnerIn, Class<LivingEntity> targetClassIn, int targetChanceIn,
			boolean checkSight, boolean nearbyOnlyIn, Predicate<LivingEntity> targetPredicate) {
		super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
		// TODO Auto-generated constructor stub
		this.shark = (SharkEntity) goalOwnerIn;
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
