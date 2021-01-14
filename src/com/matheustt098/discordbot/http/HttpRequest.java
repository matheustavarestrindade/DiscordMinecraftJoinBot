package com.matheustt098.discordbot.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map.Entry;

import com.matheustt098.discordbot.http.options.RequestMethod;

public class HttpRequest {

	private String link;
	private String method;

	boolean hasParameters = false;
	private String requestParameters;

	private HashMap<String, String> headers = new HashMap<String, String>();

	public HttpRequest(String url) {
		this.link = url;
		setMethod(RequestMethod.GET);
	}

	public void setMethod(RequestMethod method) {
		this.method = method.name();
	}

	public void setRequestPrameters(String parameters) {
		this.requestParameters = parameters;
		this.hasParameters = true;
	}

	public void addHeader(String header, String value) {
		this.headers.put(header, value);
	}

	public String execute() {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL(link);
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod(method);

			if (!headers.isEmpty()) {

				for (Entry<String, String> entry : headers.entrySet()) {
					connection.setRequestProperty(entry.getKey(), entry.getValue());
				}

			}
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Accept-Encoding", "identity");
			if (hasParameters) {
				connection.setRequestProperty("Content-Length", Integer.toString(requestParameters.getBytes().length));
			}
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			if (hasParameters) {
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(requestParameters);
				wr.close();
			}

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
			}
			rd.close();

			System.out.println("tey= " + new String(Charset.forName("UTF-8").encode(response.toString()).array()));

			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
