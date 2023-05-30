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

    public static GuildSettings[] getAllGuilds() {
        try {
            String res = sendRequest("GET", NexusHandler.getClient().getApiUri() + "/guilds");
            JSONObject json = new JSONObject(res);

            JSONArray jsonArray = json.getJSONArray("guilds");
            List<GuildSettings> guilds = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject guild = jsonArray.getJSONObject(i);
                GuildSettings guildSettings = GuildSettings.fromJson(guild);
                guilds.add(guildSettings);
            }

            return guilds.toArray(new GuildSettings[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GuildSettings getGuild(String guildId) {
        try {
            String res = sendRequest("GET", NexusHandler.getClient().getApiUri() + "/guilds/" + guildId);
            JSONObject json = new JSONObject(res);
            return GuildSettings.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GuildSettings createGuild(String guildId) {
        if (guildId == null) return null;
        try {
            JSONObject payload = new JSONObject("{\"guildId\":" + guildId + "}");
            String res = sendRequestData("POST", NexusHandler.getClient().getApiUri() + "/guilds/", payload);
            JSONObject json = new JSONObject(res);
            return GuildSettings.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteGuild(GuildSettings guildSettings) {
        if (guildSettings == null) return false;
        try {
            String res = sendRequest("DELETE", NexusHandler.getClient().getApiUri() + "/guilds/" + guildSettings.getGuildId());
            return res.contains("deleted");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static GuildSettings updateGuild(GuildSettings guildSettings) {
        if (guildSettings == null) return null;
        try {
            JSONObject payload = new JSONObject(guildSettings.toJson());
            String res = sendRequestData("PUT", NexusHandler.getClient().getApiUri() + "/guilds/" + guildSettings.getGuildId(), payload);
            JSONObject json = new JSONObject(res);
            return GuildSettings.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
