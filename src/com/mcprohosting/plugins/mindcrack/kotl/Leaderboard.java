package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class Leaderboard {

	ScoreboardManager manager;
	Scoreboard board;
	Objective objective;

	public Leaderboard() {
		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		objective = board.getObjective("Leaderboard");
		if (objective == null) {
			objective = board.registerNewObjective("Leaderboard", "dummy");
		}

		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		objective.setDisplayName("Leaderboard");
	}

	public void addPlayer(Player player) {
		Score score = objective.getScore(player);
		score.setScore(0);

		updateScoreboard();
	}

	public void updateScoreboard() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(board);
		}
	}

	public void addPoint(Player player) {
		Score score = objective.getScore(player);
		int currentScore = score.getScore();

		score.setScore(currentScore + 1);
	}

}
