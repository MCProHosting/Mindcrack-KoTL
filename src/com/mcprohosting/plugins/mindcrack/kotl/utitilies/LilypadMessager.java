package com.mcprohosting.plugins.mindcrack.kotl.utitilies;

import com.mcprohosting.plugins.mindcrack.kotl.KotL;
import org.bukkit.Bukkit;

import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.impl.MessageRequest;
import lilypad.client.connect.api.result.FutureResult;
import lilypad.client.connect.api.result.StatusCode;
import lilypad.client.connect.api.result.impl.MessageResult;

public class LilypadMessager implements Runnable {
	
	@Override
	public void run() {
		Connect connect = KotL.getConnect();
		try {
			String playerCount = Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers();
			FutureResult<MessageResult> futureResult = connect.request(new MessageRequest((String) null, "mindcrack", playerCount));
			MessageResult result = futureResult.await(5000L);
			
			if (!result.getStatusCode().equals(StatusCode.SUCCESS)) {
				Bukkit.getLogger().severe("Message send unsuccessful");
			}
		} catch (Exception e) {
			Bukkit.getLogger().severe("An error occurred while attempting to send a message.");
		}
	}
}
