package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.route.Method;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
public class NexusRequest {
	private Method method;
	private String url;
	private String data;

	private boolean hasData;
	private RequestResponse value;

	public NexusRequest(Method method, String url, String data) {
		this.method = method;
		this.url = url;
		this.data = data;
		this.hasData = true;
	}

	public NexusRequest(Method method, String url) {
		this.method = method;
		this.url = url;
		this.data = "Not supported by provided method.";
		this.hasData = false;
	}

	@SneakyThrows
	public RequestResponse execute() {
		URL url = new URL(getUrl());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(method.name().toUpperCase());
		connection.setRequestProperty("User-Agent", "NexusJavaAdapter");
		connection.setRequestProperty("Authorization", "Bearer " + NexusClient.getInstance().getProperties().getToken());

		if (hasData) {
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(data.getBytes());
			outputStream.flush();
			outputStream.close();
		}

		int responseCode = connection.getResponseCode();
		StringBuilder response = new StringBuilder();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
		} else {
			System.out.println("Failed to submit a new Nexus Request. Response-Code: " + responseCode);
		}

		connection.disconnect();

		RequestResponse res = new RequestResponse();
		res.setResponseCode(responseCode);
		res.setResponse(response.toString());

		return res;
	}
}