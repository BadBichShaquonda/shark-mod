package com.eon.sharkmod.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Shark extends WaterMobEntity {

	public Shark(EntityType<? extends WaterMobEntity> type, World worldIn) {
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

}
