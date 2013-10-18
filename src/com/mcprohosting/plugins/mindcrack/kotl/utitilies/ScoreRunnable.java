package com.mcprohosting.plugins.mindcrack.kotl.utitilies;

import java.util.Date;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import com.mcprohosting.plugins.mindcrack.kotl.Ladder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreRunnable implements Runnable {
	private Player previousKing = null;
	private boolean gameRunning;
	private Date lastMsg = new Date(0);

	@Override
	public void run() {
		if (Bukkit.getOnlinePlayers().length < 10) {
			gameRunning = false;
			Date now = new Date();
			
			if (now.getTime() - lastMsg.getTime() > 20000L) {
				lastMsg = now;
				Bukkit.broadcastMessage("A minimum of 10 players are required to start the game. Wating for more players...");
			}
		} else {
			if (gameRunning == false) {
				Bukkit.broadcastMessage("Minimum number of players reached, game starting.");
				gameRunning = true;
			}
		}
		
		if (gameRunning) {
			Ladder ladder = KotL.getLadder();
			Player player = ladder.getPlayerAtTop();
			
			if (player == null && previousKing != null) {
				previousKing.sendMessage("You are no longer King of the Ladder!");
				previousKing = null;
			} else if (player != null && !previousKing.equals(player)) {
				player.sendMessage("You are now King of the Ladder!");
				previousKing = player;
				KotL.getLeaderboard().addPoint(player, 1);
			}
		}
	}
}
