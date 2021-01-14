package com.matheustt098.discordbot.discord;

import com.matheustt098.discordbot.http.HttpRequest;
import com.matheustt098.discordbot.http.options.RequestMethod;

public class DiscordWebhook {

	private String weebhook_url;
	private String bot_image_url;
	private String bot_name;

	public DiscordWebhook(String webhook_url, String bot_name, String bot_image_url) {
		this.weebhook_url = webhook_url;
		this.bot_name = bot_name;
		this.bot_image_url = bot_image_url;
	}

	public void sendMessage(String message) {
		HttpRequest request = new HttpRequest(weebhook_url);
		request.setMethod(RequestMethod.POST);
		request.addHeader("Content-type", "application/json");
		String json_body = "{ \"username\": \"" + bot_name + "\", \"avatar_url\": \"" + bot_image_url + "\", \"content\": \"" + message + "\"}";
		request.setRequestPrameters(json_body);
		request.execute();
	}

}
