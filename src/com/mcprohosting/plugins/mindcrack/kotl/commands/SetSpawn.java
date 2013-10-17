package com.mcprohosting.plugins.mindcrack.kotl.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mcprohosting.plugins.mindcrack.kotl.SpawnHandler;

public class SetSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage(ChatColor.RED + "This command may only be performed by a player.");
			return true;
		}
		
		if (args.length == 1) {
			if (!cs.hasPermission("kotl.setspawn")) {
				cs.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
				return true;
			}
			
			int spawn = 0;
			try {
				spawn = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				cs.sendMessage(ChatColor.RED + "Please enter a number between 1 and 4.");
				return true;
			}
			
			if (spawn > 4 || spawn < 1) {
				cs.sendMessage(ChatColor.RED + "Please enter a number between 1 and 4.");
				return true;
			}
			
			Player player = (Player) cs;
			SpawnHandler.setSpawnLocation(spawn, player.getLocation());
			player.sendMessage("Spawn point #" + spawn + " has been set!");
			return true;
		} else {
			return false;
		}
	}
}
