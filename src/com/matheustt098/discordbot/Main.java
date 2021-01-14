package com.matheustt098.discordbot;

import org.bukkit.plugin.java.JavaPlugin;

import com.matheustt098.discordbot.discord.DiscordWebhook;
import com.matheustt098.discordbot.listeners.JoinListener;
import com.matheustt098.discordbot.listeners.PlayerDeathListener;
import com.matheustt098.discordbot.listeners.QuitListener;

public class Main extends JavaPlugin {

	private static DiscordWebhook bot;
	private static String join_message;
	private static String leave_message;
	private static String death_message_enviroiment;
	private static String death_message_player;

	public void onEnable() {

		getConfig().addDefault("webhook_url", "");
		getConfig().addDefault("avatar_url", "");
		getConfig().addDefault("bot_name", "Bot Name");
		getConfig().addDefault("join_message", "{PlayerName} has joined the server");
		getConfig().addDefault("leave_message", "{PlayerName} has leaved the server");
		getConfig().addDefault("death_message_enviroiment", "{PlayerName} has died");
		getConfig().addDefault("death_message_player", "{PlayerName1} has killed {PlayerName2}");
		getConfig().options().copyDefaults(true);
		saveConfig();
		if (getConfig().getString("webhook_url").length() < 10) {
			System.out.println("Discord bot disactivated, please insert a valid webhook url");
		} else {
			bot = new DiscordWebhook(getConfig().getString("webhook_url"), getConfig().getString("bot_name"), getConfig().getString("avatar_url"));
			join_message = getConfig().getString("join_message");
			leave_message = getConfig().getString("leave_message");
			death_message_player = getConfig().getString("death_message_player");
			death_message_enviroiment = getConfig().getString("death_message_enviroiment");
		}

		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new QuitListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);

	}

	public static DiscordWebhook getDiscordBot() {
		return bot;
	}

	public static DiscordWebhook getBot() {
		return bot;
	}

	public static String getJoin_message() {
		return join_message;
	}

	public static String getLeave_message() {
		return leave_message;
	}

	public static String getDeath_message_enviroiment() {
		return death_message_enviroiment;
	}

	public static String getDeath_message_player() {
		return death_message_player;
	}

}
