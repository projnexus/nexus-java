package cc.projectnexus.adapters.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NexusRequester {
	private static final String USER_AGENT = "NEXUS_JAVA_ADAPTER";

	public static String sendGetRequest(String url, String bearerToken) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		// Set request method
		connection.setRequestMethod("GET");

		// Set request headers
		setCommonHeaders(connection, bearerToken);

		int responseCode = connection.getResponseCode();
		StringBuilder response = new StringBuilder();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
		}

		connection.disconnect();

		return response.toString();
	}

	public static String sendPostRequest(String url, String data, String bearerToken) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		// Set request method
		connection.setRequestMethod("POST");

		// Set request headers
		setCommonHeaders(connection, bearerToken);

		// Enable output stream and write request body data
		setRequestBody(connection, data);

		int responseCode = connection.getResponseCode();
		StringBuilder response = new StringBuilder();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
		}

		connection.disconnect();

		return response.toString();
	}

	public static String sendPatchRequest(String url, String data, String bearerToken) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		// Set request method
		connection.setRequestMethod("PATCH");

		// Set request headers
		setCommonHeaders(connection, bearerToken);

		// Enable output stream and write request body data
		setRequestBody(connection, data);

		int responseCode = connection.getResponseCode();
		StringBuilder response = new StringBuilder();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
		}

		connection.disconnect();

		return response.toString();
	}

	public static String sendDeleteRequest(String url, String bearerToken) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		// Set request method
		connection.setRequestMethod("DELETE");

		// Set request headers
		setCommonHeaders(connection, bearerToken);

		int responseCode = connection.getResponseCode();
		StringBuilder response = new StringBuilder();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
		}

		connection.disconnect();

		return response.toString();
	}

	private static void setCommonHeaders(HttpURLConnection connection, String bearerToken) {
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
	}

	private static void setRequestBody(HttpURLConnection connection, String data) throws IOException {
		connection.setRequestProperty("Content-Type", "application/json");

		// Enable output stream and write request body data
		connection.setDoOutput(true);
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(data.getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
