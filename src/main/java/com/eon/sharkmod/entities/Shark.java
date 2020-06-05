package com.eon.sharkmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;

public class Shark extends MonsterEntity {
	   
	public Shark(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, 1.5D, 10));
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	

}
