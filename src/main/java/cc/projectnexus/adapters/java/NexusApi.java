package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NexusApi {
	public static String API_URL = "https://api.projectnexus.cc/";

	public static String provideRequest(Method method, String url) {
		switch (method) {
			case GET -> {
				try {
					return provideRequestAsGet(url);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			case POST -> {

			}
			default -> {
				return "";
			}
		}
		return "";
	}

	public static boolean attempt(String token) {

	}

	private static String provideRequestAsGet(String url) throws IOException {
		URL apiUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder res = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				res.append(inputLine);
			}

			in.close();
			return res.toString();
		} else {
			throw new IOException("Get request failed. Nexus-API Response Code: " + responseCode);
		}
	}

}
