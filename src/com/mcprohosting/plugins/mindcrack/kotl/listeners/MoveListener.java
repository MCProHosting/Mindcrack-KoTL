package com.mcprohosting.plugins.mindcrack.kotl.listeners;

import com.mcprohosting.plugins.mindcrack.kotl.utitilies.Helpers;
import com.mcprohosting.plugins.mindcrack.kotl.utitilies.SpawnHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (!Helpers.isHeadMovement(event)) { //Don't bother doing computation on head movement
			if (event.getTo().getY() < 0) {
				SpawnHandler.randomlySpawnPlayer(event.getPlayer());
			}
		}
	}
}
