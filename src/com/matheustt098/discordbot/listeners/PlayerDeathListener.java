package com.matheustt098.discordbot.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.matheustt098.discordbot.Main;

public class PlayerDeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (e.getEntity().getKiller() != null && e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity();
			Player k = e.getEntity().getKiller();
			String msg = Main.getDeath_message_player().replace("{PlayerName1}", p.getName()).replace("{PlayerName2}", k.getName());
			Main.getBot().sendMessage(msg);
		} else {
			Player p = e.getEntity();
			String msg = Main.getDeath_message_enviroiment().replace("{PlayerName}", p.getName());
			Main.getBot().sendMessage(msg);
		}
	}

}
