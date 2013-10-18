package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.entity.Player;

public class ScoreRunnable implements Runnable {

	@Override
	public void run() {
		Ladder ladder = KotL.getLadder();
		ladder.update();
		Player player = ladder.getPlayerAtTop();
		if (player != null) {
			KotL.getLeaderboard().addPoint(player, 1);
		}
	}
}
