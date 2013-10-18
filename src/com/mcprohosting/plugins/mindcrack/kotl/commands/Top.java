package com.mcprohosting.plugins.mindcrack.kotl.commands;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Top implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Player player = (Player) cs;
			if (KotL.getLeaderboard().getTop() != null) {
				player.sendMessage("Top Player is " + KotL.getLeaderboard().getTop() + " with a time of " +
					KotL.getLeaderboard().getTopScore() + " seconds!");
			} else {
				player.sendMessage("There is no top player at this time!");
			}
			return true;
		} else {
			return false;
		}
	}
}
