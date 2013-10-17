package com.mcprohosting.plugins.mindcrack.kotl;

import com.mcprohosting.plugins.mindcrack.kotl.commands.SetLadder;
import com.mcprohosting.plugins.mindcrack.kotl.commands.SetSpawn;
import com.mcprohosting.plugins.mindcrack.kotl.listeners.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class KotL extends JavaPlugin {
	private static Plugin plugin;
	private static Ladder ladder;
	private static Leaderboard leaderboard;
	private static Database database;

	public void onEnable() {
		plugin = this;
		init();
	}

	public void onDisable() {
		getLogger().info("Disabled");
	}

	private void init() {
		saveDefaultConfig();

		SpawnHandler.setupSpawnsFromConfiguration();
		registerListeners();

		database = new Database();
		leaderboard = new Leaderboard();
		ladder = Ladder.fromConfig();
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new ScoreRunnable(), 20, 20);

		registerCommands();
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new Player(), this);
	}

	private void registerCommands() {
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("setladder").setExecutor(new SetLadder());
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
