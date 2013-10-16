package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
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
			if (!checkPlayerOnLadder(player)) {
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
		int ladderAbsolute = this.getX()+this.getZ();
		int playerAbsolute = (int) player.getLocation().getX() + (int) player.getLocation().getZ();

		//If the player is within 4 blocks (2 in each direction) of the ladder then return true.
		if (ladderAbsolute == playerAbsolute || (ladderAbsolute-2 > playerAbsolute && ladderAbsolute+2 < playerAbsolute)) {
			return true;
		} else {
			return false;
		}
	}
}
