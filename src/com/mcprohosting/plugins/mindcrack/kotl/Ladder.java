package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class Ladder {
	int x;
	int z;
	int topY;
	Player playerAtTop;
	HashSet<Player> playersOnLadder;

	public Ladder(int x, int z, int topY) {
		this.x = x;
		this.z = z;
		this.topY = topY;
		playersOnLadder = new HashSet<Player>();
	}

	public Player getPlayerAtTop() {
		if (playersOnLadder.size() > 0) {
			Player lastHighest = (Player) playersOnLadder.toArray()[0];

			for (Player player : playersOnLadder) {
				if (player.getLocation().getY() > lastHighest.getLocation().getY()) {
					lastHighest = player;
				}
			}

			return lastHighest;
		} else {
			return null;
		}
	}

	public void update() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (checkPlayerOnLadder(player)) {
				playersOnLadder.add(player);
			} else {
				playersOnLadder.remove(player);
			}
		}
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

	private boolean checkPlayerOnLadder(Player player) {
		int playerX = player.getLocation().getBlockX();
		int playerZ = player.getLocation().getBlockZ();
		
		if (playerX <= x + 1 && playerX >= x - 1 && playerZ <= z + 1 && playerZ >= z - 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Ladder fromConfig() {
		ConfigurationSection ladderConfig = KotL.getPlugin().getConfig().getConfigurationSection("ladder");
		Ladder ladder = new Ladder(ladderConfig.getInt("x"), ladderConfig.getInt("z"), ladderConfig.getInt("topY"));
		return ladder;
	}
}
