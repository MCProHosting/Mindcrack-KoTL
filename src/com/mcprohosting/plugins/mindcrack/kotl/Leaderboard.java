package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

public class Leaderboard {

	ScoreboardManager manager;
	Scoreboard board;
	Objective objective;
	Map<String, Integer> points;

	public Leaderboard() {
		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		objective = board.getObjective("Leaderboard");
		if (objective == null) {
			objective = board.registerNewObjective("Leaderboard", "dummy");
		}

		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		objective.setDisplayName("Leaderboard");
		points = new HashMap<String, Integer>();
	}

	public void addPlayer(Player player) {
		Score score = objective.getScore(player);
		score.setScore(0);

		if (!points.containsKey(player.getName().toLowerCase())) {
			points.put(player.getName().toLowerCase(), 0);
		}

		updateScoreboard();
	}

	public void removePlayer(String player) {
		if (points.containsKey(player.toLowerCase())) {
			points.remove(player);
		}
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

		if (points.containsKey(player.getName().toLowerCase())) {
			int tempPoints = points.get(player.getName().toLowerCase()).intValue();
			points.put(player.getName().toLowerCase(), tempPoints + 1);
		}
	}

	public int getTempPoints (String name) {
		if (points.containsKey(name.toLowerCase())) {
			return points.get(name.toLowerCase());
		}

		return 0;
	}

	public void resetTempPoints(String name) {
		if (points.containsKey(name.toLowerCase())) {
			points.put(name.toLowerCase(), 0);
		}
	}

}
