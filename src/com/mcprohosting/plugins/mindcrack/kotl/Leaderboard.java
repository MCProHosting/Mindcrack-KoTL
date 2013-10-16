package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class Leaderboard {

	ScoreboardManager manager;
	Scoreboard board;
	Team team;
	Objective objective;

	public Leaderboard() {
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
		team = board.registerNewTeam("Players");
		objective = board.registerNewObjective("Leaderboard", "dummy");

		team.setCanSeeFriendlyInvisibles(false);
		team.setAllowFriendlyFire(false);

		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("Leaderboard");
	}

	public void addPlayer(Player player) {
		team.addPlayer(player);

		Score score = objective.getScore(player);
		score.setScore(0);
		updateScoreboard();
	}

	public void addPlayers() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			addPlayer(player);

			player.setScoreboard(board);
		}
	}

	public void updateScoreboard() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(board);
		}
	}

	public void removePlayer(Player player) {
		team.removePlayer(player);
	}

	public void addPoint(Player player) {
		Score score = objective.getScore(player);
		int currentScore = score.getScore();

		score.setScore(currentScore + 1);
	}

}
