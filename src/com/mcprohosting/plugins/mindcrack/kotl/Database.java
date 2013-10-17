package com.mcprohosting.plugins.mindcrack.kotl;

import com.gmail.favorlock.bonesqlib.MySQL;
import org.bukkit.configuration.ConfigurationSection;

public class Database {

	private static MySQL mysql;
	private String host;
	private int port;
	private String database;
	private String username;
	private String password;

	public Database() {
		init();
	}

	private void init() {
		loadConfig();
		mysql = new MySQL(KotL.getPlugin().getLogger(),
				"KoTL",
				host,
				port,
				database,
				username,
				password);
		mysql.open();
	}

	private void loadConfig() {
		ConfigurationSection databaseConfig = KotL.getPlugin().getConfig().getConfigurationSection("database");

		for (int i = 1; i <= databaseConfig.getKeys(false).size(); i++) {
			host = databaseConfig.getString("host");
			port = databaseConfig.getInt("port");
			database = databaseConfig.getString("database");
			username = databaseConfig.getString("username");
			password = databaseConfig.getString("password");
		}
	}

}
