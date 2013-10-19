package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Ladder {
	int x;
	int z;
	int topY;
	int centerY;
	Player playerAtTop;

	public Ladder(int x, int z, int topY) {
		this.x = x;
		this.z = z;
		this.topY = topY;
		this.centerY = 62 + ((topY - 62) / 2);
	}

	public Player getPlayerAtTop() {
		Player kingOfTheLadder = null;

		if (Bukkit.getOnlinePlayers().length > 0) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				int playerX = player.getLocation().getBlockX();
				int playerZ = player.getLocation().getBlockZ();
				
				if (playerX <= x + 1 && playerX >= x - 1 && player.getLocation().getBlockY() == topY && playerZ <= z + 1 && playerZ >= z - 1) {
					if (kingOfTheLadder == null) {
						kingOfTheLadder = player;
					} else {
						return null;
					}
				}
			}
		} else {
			return null;
		}

		return kingOfTheLadder;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public int getTopY() {
		return topY;
	}

	public int getCenterY() {
		return centerY;
	}
	
	public void setLadderLocation(Location location) {
		x = location.getBlockX();
		topY = location.getBlockY();
		z = location.getBlockZ();
		
		ConfigurationSection section = new YamlConfiguration();
		section.set("x", x);
		section.set("topY", topY);
		section.set("z", z);
		KotL.getPlugin().getConfig().set("ladder", section);
		KotL.getPlugin().saveConfig();
	}
	
	public static Ladder fromConfig() {
		ConfigurationSection ladderConfig = KotL.getPlugin().getConfig().getConfigurationSection("ladder");
		Ladder ladder = new Ladder(ladderConfig.getInt("x"), ladderConfig.getInt("z"), ladderConfig.getInt("topY"));
		return ladder;
	}
}
