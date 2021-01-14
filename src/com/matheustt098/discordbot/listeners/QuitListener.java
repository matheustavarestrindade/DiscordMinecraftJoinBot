package com.matheustt098.discordbot.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.matheustt098.discordbot.Main;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (e.getPlayer() == null) {
			return;
		}
		if (!(e.getPlayer() instanceof Player)) {
			return;
		}

		Player p = e.getPlayer();
		String msg = Main.getLeave_message().replace("{PlayerName}", p.getName());
		Main.getBot().sendMessage(msg);
	}

}
