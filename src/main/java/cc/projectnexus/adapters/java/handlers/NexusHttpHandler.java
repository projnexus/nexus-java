package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Region;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NexusHttpHandler {

    public static String sendRequest(String method, String url) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", NexusHandler.getClient().getApiKey());
            connection.setRequestProperty("User-Agent", "NexusBot");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } else {
                System.out.println("Failed to send GET request to " + url + ". Response code: " + responseCode);
            }
        } finally {
            // Prevent memory leaks by closing when done
            if (reader != null) reader.close();
            if (connection != null) connection.disconnect();
        }

        return response.toString();
    }

    private static String sendRequestData(String method, String url, JSONObject payload) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        OutputStream outputStream = null;
        StringBuilder response = new StringBuilder();

        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", NexusHandler.getClient().getApiKey());
            connection.setRequestProperty("User-Agent", "NexusBot");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            System.out.println(String.valueOf(payload));

            outputStream = connection.getOutputStream();
            outputStream.write(payload.toString().getBytes());
            outputStream.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } else {
                System.out.println("Failed to send POST request to " + url + ". Response code: " + responseCode);
            }
        } finally {
            if (reader != null) reader.close();
            if (connection != null) connection.disconnect();
            if (outputStream != null) outputStream.close();
        }

        return response.toString();
    }

    // Check if the token the user provided is valid
    public static boolean test() {
        try {
            String res = sendRequest("GET", NexusHandler.getClient().getApiUri() + "/test");
            // Using contains() as we cannot access the response code.
            // The response will always have a body, so we cannot check if it is empty.
            if (res.contains("authenticated")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static GuildSettings getGuildSettings(String guildId) {
        try {
            String res = sendRequest("GET", NexusHandler.getClient().getApiUri() + "/guilds/" + guildId);
            JSONObject json = new JSONObject(res);

            // Parse regions array into an array of Region objects
            JSONArray jsonArray = json.getJSONArray("enabled_regions");
            Region[] array = new Region[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                String regionName = jsonArray.getString(i);
                Region region = Region.getRegionByIdentifier(regionName);
                array[i] = region;
            }

            return new GuildSettings(
                json.getLong("id"),                                 // id
                json.getString("guildId"),                          // guildId
                json.getBoolean("auto_ban"),                        // autoBan
                json.getBoolean("auto_unban"),                      // autoUnban
                json.getString("logs_channel"),                     // logsChannel
                Timestamp.valueOf(json.getString("createdAt")),     // createdAt
                Timestamp.valueOf(json.getString("updatedAt")),     // lastUpdated
                array                                                    // enabledRegions
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
