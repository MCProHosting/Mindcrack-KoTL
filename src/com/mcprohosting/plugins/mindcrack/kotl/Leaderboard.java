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
	Map<String, Participant> participants;
	String top;
	int topScore;

	public Leaderboard() {
		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		objective = board.getObjective("Leaderboard");
		if (objective == null) {
			objective = board.registerNewObjective("Leaderboard", "dummy");
		}

		objective.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		objective.setDisplayName("Leaderboard");
		participants = new HashMap<String, Participant>();
		top = null;
		topScore = 0;
	}

	public void addPlayer(Player player) {
		Score score = objective.getScore(player);
		score.setScore(0);

		if (!participants.containsKey(player.getName().toLowerCase())) {
			participants.put(player.getName().toLowerCase(), new Participant(player));
		}

		updateScoreboard();
	}

	public void removePlayer(String player) {
		if (participants.containsKey(player.toLowerCase())) {
			participants.remove(player);
		}
	}

	public void updateScoreboard() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.setScoreboard(board);
		}
	}

	public void addPoint(Player player, int point) {
		Score score = objective.getScore(player);
		int currentScore = score.getScore();

		score.setScore(currentScore + 1);

		if (participants.containsKey(player.getName().toLowerCase())) {
			Participant participant = participants.get(player.getName().toLowerCase());
			int tempPoints = participant.getUpdateScore();
			participant.setUpdateScore(tempPoints + point);

			if (point == 1) {
				int totalPoints = participant.getTotalScore();
				participant.setTotalScore(totalPoints + point);
				participant.updateTimeStreak();
				participant.calculateStreakPoints();
				isTop(player.getName());
			}
		}
	}

	public int getGlobalPoints (String name) {
		if (participants.containsKey(name.toLowerCase())) {
			Participant participant = participants.get(name.toLowerCase());
			return participant.getUpdateScore();
		}

		return 0;
	}

	public void resetTempPoints(String name) {
		if (participants.containsKey(name.toLowerCase())) {
			Participant participant = participants.get(name.toLowerCase());
			participant.setUpdateScore(0);
		}
	}

	public void isTop(String name) {
		if (!(top == null)) {
			if (top == name) {
				topScore = participants.get(top.toLowerCase()).getTotalScore();
				return;
			}

			int topScore = participants.get(top.toLowerCase()).getTotalScore();
			int challengerScore = participants.get(name.toLowerCase()).getTotalScore();
			KotL.getPlugin().getLogger().info("Score: " + topScore);

			if (challengerScore > topScore) {
				top = name;
				this.topScore = challengerScore;
			}
		} else {
			top = name;
			topScore = participants.get(top.toLowerCase()).getTotalScore();
		}
	}

	public String getTop() {
		return top;
	}

	public int getTopScore() {
		return topScore;
	}

	public Participant getParticipant(String name) {
		if (participants.containsKey(name.toLowerCase())) {
			return participants.get(name);
		}
		return null;
	}

}
