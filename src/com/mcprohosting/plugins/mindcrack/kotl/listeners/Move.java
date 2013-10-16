package com.mcprohosting.plugins.mindcrack.kotl.listeners;

import com.mcprohosting.plugins.mindcrack.kotl.Helpers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move {
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (!Helpers.isHeadMovement(event)) { //Don't bother doing computation on head movement
			if (event.getTo().getY() < 0) {

			}
		}
	}
}
