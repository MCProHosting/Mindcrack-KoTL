package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.mcprohosting.plugins.mindcrack.kotl.listeners.Player;

public class KotL extends JavaPlugin {
	private static Plugin plugin;
	private static Ladder ladder;
	private static Leaderboard leaderboard;

	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		
		SpawnHandler.setupSpawnsFromConfiguration();
		
		getServer().getPluginManager().registerEvents(new Player(), this);

		leaderboard = new Leaderboard();
		leaderboard.addPlayers();

		getLogger().info("Initialized");
	}

	public void startLadderUpdater() {
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("This is a test");
				getLadder().update();
			}
		}, 20, 20);
	}

	public void onDisable() {
		getLogger().info("Disabled");
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public static Ladder getLadder() {
		return ladder;
	}

	public static Leaderboard getLeaderboard() {
		return leaderboard;
	}
}
