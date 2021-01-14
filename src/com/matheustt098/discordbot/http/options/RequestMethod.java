package com.matheustt098.discordbot.http.options;

public enum RequestMethod {

	POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE");

	private final String name;

	private RequestMethod(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
