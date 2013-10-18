package com.mcprohosting.plugins.mindcrack.kotl.database;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

	public void init() {
		if (!(Database.setupTables() || Database.isDBSchemaValid())) {
			KotL.getPlugin().getLogger().info("Unable to create tables or validate schema." +
				" Plugin will not load until issue is fixed");
			Bukkit.getPluginManager().disablePlugin(KotL.getPlugin());
		}
	}

	public static Connection getConnection() {
		return KotL.getDB().mysql.getConnection();
	}

	public static boolean containsPlayer(String player) {
		boolean retVal = false;

		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("SELECT LOWER(player) FROM players WHERE player = LOWER(?);");
			ps.setString(1, player);
			rs = ps.executeQuery();

			retVal = rs.next();

			rs.close();
			ps.close();
			Database.mysql.closeConnection(connection);
		} catch (SQLException e) {
			KotL.getPlugin().getLogger().info("Failed to check if the database contains a player.");
			Database.mysql.closeConnection(connection);
			return false;
		}

		return retVal;
	}

	public static boolean addPlayer(String player) {
		boolean retVal = false;

		Connection connection = getConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement("INSERT INTO players (player, global) " +
				"VALUES (?, 0);");
			ps.setString(1, player);
			retVal = ps.executeUpdate() > 0;
			ps.close();
			Database.mysql.closeConnection(connection);
		} catch (SQLException e) {
			KotL.getPlugin().getLogger().info("Failed to add a player to the database.");
			Database.mysql.closeConnection(connection);
			return false;
		}

		return retVal;
	}

	public static boolean addPoints(String player, int points) {
		boolean retVal = false;

		Connection connection = getConnection();
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement("UPDATE players SET global=global +? WHERE LOWER(player)=(?);");
			ps.setInt(1, points);
			ps.setString(2, player);
			retVal = ps.executeUpdate() > 0;
			ps.close();
			Database.mysql.closeConnection(connection);
		} catch (SQLException e) {
			KotL.getPlugin().getLogger().info("Failed to add points to a players global score in the database.");
			Database.mysql.closeConnection(connection);
			return false;
		}

		return retVal;
	}

}
