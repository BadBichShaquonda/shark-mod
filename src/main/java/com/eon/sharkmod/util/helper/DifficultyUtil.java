package com.eon.sharkmod.util.helper;

import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class DifficultyUtil {
	private World world;
	private Difficulty difficulty;
	
	public DifficultyUtil(World world) {
		this.world = world;
		this.difficulty = world.getDifficulty();
	}

}
