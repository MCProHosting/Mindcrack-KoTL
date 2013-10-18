package com.mcprohosting.plugins.mindcrack.kotl;

import com.mcprohosting.plugins.mindcrack.kotl.database.Database;
import com.mcprohosting.plugins.mindcrack.kotl.database.DatabaseManager;
import com.mcprohosting.plugins.mindcrack.kotl.database.MySQLRunnable;
import com.mcprohosting.plugins.mindcrack.kotl.listeners.PlayerListener;
import com.mcprohosting.plugins.mindcrack.kotl.utitilies.LilypadMessager;
import com.mcprohosting.plugins.mindcrack.kotl.utitilies.ScoreRunnable;
import com.mcprohosting.plugins.mindcrack.kotl.utitilies.SpawnHandler;

import lilypad.client.connect.api.Connect;

import com.mcprohosting.plugins.mindcrack.kotl.commands.SetLadder;
import com.mcprohosting.plugins.mindcrack.kotl.commands.SetSpawn;
import com.mcprohosting.plugins.mindcrack.kotl.commands.Top;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class KotL extends JavaPlugin {
	private static Plugin plugin;
	private static Ladder ladder;
	private static Leaderboard leaderboard;
	private static Database database;
	private static DatabaseManager databaseManager;
	private static Connect connect;

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
		
		connect = plugin.getServer().getServicesManager().getRegistration(Connect.class).getProvider();

		database = new Database();
		databaseManager = new DatabaseManager();
		databaseManager.init();
		leaderboard = new Leaderboard();
		ladder = Ladder.fromConfig();
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new ScoreRunnable(), 20, 20);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new MySQLRunnable(), 20, 20 * 60);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new LilypadMessager(), 20, 20);

		registerCommands();
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}

	private void registerCommands() {
		getCommand("setspawn").setExecutor(new SetSpawn());
		getCommand("setladder").setExecutor(new SetLadder());
		getCommand("top").setExecutor(new Top());
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

	public static Database getDB() {
		return database;
	}
	
	public static Connect getConnect() {
		return connect;
	}
}
