package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Random;

public class SpawnHandler {
	private static ArrayList<Location> spawns = new ArrayList<Location>();
	private static Random random = new Random();

	//Only run this once, pulls spawn data from configuration.
	public static void setupSpawnsFromConfiguration() {
		ConfigurationSection spawnsConfig = KotL.getPlugin().getConfig().getConfigurationSection("spawns");
		
		for (int i = 1; i <= spawnsConfig.getKeys(false).size(); i++) {
			ConfigurationSection spawn = spawnsConfig.getConfigurationSection("spawn" + i);
			Location location = new Location(Bukkit.getWorlds().get(0), spawn.getDouble("x"), spawn.getDouble("y"), spawn.getDouble("z"), new Float(spawn.getDouble("yaw")), new Float(spawn.getDouble("pitch")));
			spawns.add(location);
		}
	}
	
	public static Location getRandomSpawnLocation() {
		return spawns.get(random.nextInt(4));
	}
}
