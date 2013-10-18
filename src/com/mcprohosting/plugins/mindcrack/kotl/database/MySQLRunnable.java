package com.mcprohosting.plugins.mindcrack.kotl.database;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MySQLRunnable implements Runnable {

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			int points = KotL.getLeaderboard().getGlobalPoints(player.getName());
			if (points > 0) {
				DatabaseManager.addPoints(player.getName(), points);
				KotL.getLeaderboard().resetTempPoints(player.getName());
			}
		}
	}

}
