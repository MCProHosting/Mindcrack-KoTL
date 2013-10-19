package com.mcprohosting.plugins.mindcrack.kotl.utitilies;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.RedirectRequest;
import lilypad.client.connect.api.result.FutureResult;
import lilypad.client.connect.api.result.StatusCode;
import lilypad.client.connect.api.result.impl.RedirectResult;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class Helpers {
	public static boolean isHeadMovement(PlayerMoveEvent event) {
		if (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()) {
			return true;
		} else {
			return false;
		}
 	}

	public static void redirectToServer(String server, final Player player) {
		try {
			FutureResult<RedirectResult> futureResult = KotL.getConnect().request(new RedirectRequest(server, player.getName()));
			RedirectResult result = futureResult.await(5000L);
			if (!result.getStatusCode().equals(StatusCode.SUCCESS)) {
				player.sendMessage(ChatColor.RED.toString() + "That server is current not available: !");
				Bukkit.getLogger().warning("RedirectRequest failed for player: " + player.getName());
			}
		} catch (RequestException e) {
			player.sendMessage(ChatColor.RED.toString() + "That server is current not available: " + e.getCause() + "!");
		} catch (InterruptedException e) {
			player.sendMessage(ChatColor.RED.toString() + "That server is current not available: " + e.getCause() + "!");
			Bukkit.getLogger().warning("RedirectRequest interrupted for player: " + player.getName());
		}
	}
}
