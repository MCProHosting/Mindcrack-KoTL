package com.mcprohosting.plugins.mindcrack.kotl.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;

public class SetLadder implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "This command may only be performed by a player.");
			return true;
		}
		
		if (args.length == 0) {
			if (!cs.hasPermission("kotl.setladder")) {
				cs.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
				return true;
			}
			
			Player player = (Player) cs;
			KotL.getLadder().setLadderLocation(player.getLocation());
			player.sendMessage("Ladder position has been set!");
			return true;
		} else {
			return false;
		}
	}
}
