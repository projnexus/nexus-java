package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NexusApi {
	public static String API_URL = "https://api.projectnexus.cc/";

	public static String provideRequest(Method method, String data, String url) throws TokenNotAuthorizedException {
		switch (method) {
			case GET -> {
				try {
					NexusRequester.sendGetRequest(url, NexusClient.getInstance().getProperties().getToken());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			case POST -> {
				try {
					NexusRequester.sendPostRequest(url, data, NexusClient.getInstance().getProperties().getToken());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			case PATCH -> {
				try {
					NexusRequester.sendPatchRequest(url, data, NexusClient.getInstance().getProperties().getToken());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			case DELETE -> {
				try {
					NexusRequester.sendDeleteRequest(url, NexusClient.getInstance().getProperties().getToken());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			default -> {
				return "";
			}
		}
		return "";
	}

	/**
	 * Checking if the token is authorized.
	 * @param token The token you want to attempt.
	 * @return A boolean if the request returns response code HTTP_OK (200)
	 * @throws IOException Any exception thrown while executing the HTTP-request.
	 */
	public static boolean attempt(String token) throws IOException {
		URL apiUrl = new URL(Route.GuildsRoutes.GET_AMOUNT_COUNT);
		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", "Bearer " + NexusClient.getInstance().getProperties().getToken());

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			return true;
		} else {
			return false;
		}
	}

	private static String provideRequestAsGet(String url) throws IOException {
		URL apiUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", "Bearer " + NexusClient.getInstance().getProperties().getToken());

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
