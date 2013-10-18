package com.mcprohosting.plugins.mindcrack.kotl.utitilies;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import com.mcprohosting.plugins.mindcrack.kotl.Ladder;
import org.bukkit.entity.Player;

public class ScoreRunnable implements Runnable {

	@Override
	public void run() {
		Ladder ladder = KotL.getLadder();
		Player player = ladder.getPlayerAtTop();
		if (player != null) {
			KotL.getLeaderboard().addPoint(player, 1);
		}
	}
}
