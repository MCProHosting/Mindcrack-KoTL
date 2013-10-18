package com.mcprohosting.plugins.mindcrack.kotl.utitilies;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
	
	private static Location getRandomSpawnLocation() {
		Location spawn = spawns.get(random.nextInt(4));
		if (spawn.getBlockY() == 0) {
			return null;
		}
		return spawn;
	}
	
	public static void randomlySpawnPlayer(Player player) {
		Location location = getRandomSpawnLocation();
		
		if (location != null) {
			PlayerInventory inventory = player.getInventory();
			inventory.clear();
			inventory.addItem(new ItemStack(Material.STONE_SWORD));
			inventory.setHelmet(new ItemStack(Material.IRON_HELMET));
			inventory.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			inventory.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			inventory.setBoots(new ItemStack(Material.IRON_BOOTS));
			
			player.teleport(location);
		}
	}
	
	public static void setSpawnLocation(int spawnNumber, Location location) {
		spawns.set(spawnNumber - 1, location);
		
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		ConfigurationSection section = new YamlConfiguration();
		section.set("x", x);
		section.set("y", y);
		section.set("z", z);
		section.set("yaw", yaw);
		section.set("pitch", pitch);
		KotL.getPlugin().getConfig().set("spawns.spawn" + spawnNumber, section);
		KotL.getPlugin().saveConfig();
	}
}
