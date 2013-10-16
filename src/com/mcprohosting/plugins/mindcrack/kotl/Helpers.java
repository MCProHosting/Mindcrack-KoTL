package com.mcprohosting.plugins.mindcrack.kotl;

import org.bukkit.event.player.PlayerMoveEvent;

public class Helpers {
	public static boolean isHeadMovement(PlayerMoveEvent event) {
		if (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()) {
			return true;
		} else {
			return false;
		}
 	}
}
