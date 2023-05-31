package cc.projectnexus.adapters.java.api;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;

import static cc.projectnexus.adapters.java.utils.JavaScriptUtils.*;

public class ApiInteraction {

    private static String uri = NexusClient.getNexusInstance().getApiUri();

    public static String sendRequest(String method, String url) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", NexusClient.getNexusInstance().getApiKey());
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

    private static String sendRequestData(String method, String url, String payload) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        OutputStream outputStream = null;
        StringBuilder response = new StringBuilder();

        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", NexusClient.getNexusInstance().getApiKey());
            connection.setRequestProperty("User-Agent", "NexusBot");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            System.out.println(String.valueOf(payload));

            outputStream = connection.getOutputStream();
            outputStream.write(payload.getBytes());
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
            String res = sendRequest("GET", NexusClient.getNexusInstance().getApiUri() + "/test");
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
            String res = sendRequest("GET", uri + "/guilds");
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonArray("guilds");
            if (jsonArray != null) {
                GuildSettings[] guilds = gson.fromJson(jsonArray, GuildSettings[].class);

                for (GuildSettings guild : guilds) {
                    setTimestampFields(guild, gson.toJson(guild));
                }

                return guilds;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GuildSettings getGuild(String guildId) {
        if (guildId == null) return null;
        try {
            Gson gson = new Gson();
            String res = sendRequest("GET", uri + "/guilds/" + guildId);
            GuildSettings guildSettings = gson.fromJson(res, GuildSettings.class);
            setTimestampFields(guildSettings, res);
            return guildSettings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static GuildSettings createGuild(String guildId) {
        if (guildId == null) return null;
        try {
            Gson gson = new Gson();
            String payload = "{\"guildId\": \"" + guildId + "\"}";
            String res = sendRequestData("POST", uri + "/guilds/", payload);
            GuildSettings guildSettings = gson.fromJson(res, GuildSettings.class);
            setTimestampFields(guildSettings, res);
            return guildSettings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteGuild(GuildSettings guildSettings) {
        if (guildSettings == null) return false;
        try {
            String res = sendRequest("DELETE", uri + "/guilds/" + guildSettings.getGuildId());
            return res.contains("deleted");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static GuildSettings updateGuild(GuildSettings guildSettings) {
        if (guildSettings == null) return null;
        try {
            Gson gson = new Gson();
            String payload = gson.toJson(guildSettings);
            String res = sendRequestData("PUT", uri + "/guilds/" + guildSettings.getGuildId(), payload);
            GuildSettings updatedGuildSettings = gson.fromJson(res, GuildSettings.class);
            setTimestampFields(updatedGuildSettings, res);
            return updatedGuildSettings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Infraction[] getAllInfractions() {
        try {
            String res = sendRequest("GET", uri + "/infractions");
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
            JsonArray jsonArray = jsonObject.getAsJsonArray("infractions");
            if (jsonArray != null) {
                Infraction[] infractions = gson.fromJson(jsonArray, Infraction[].class);

                for (Infraction infraction : infractions) {
                    setTimestampFields(infraction, gson.toJson(infraction));
                }

                return infractions;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Infraction getInfraction(Long infractionId) {
        if (infractionId == null) return null;
        try {
            Gson gson = new Gson();
            String res = sendRequest("GET", uri + "/infractions/" + infractionId);
            Infraction infraction = gson.fromJson(res, Infraction.class);
            setTimestampFields(infraction, res);
            return infraction;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Infraction createInfraction(Infraction infraction) {
        if (infraction == null) return null;
        try {
            Gson gson = new Gson();
            String payload = gson.toJson(infraction);
            String res = sendRequestData("POST", uri + "/infractions/", payload);
            return gson.fromJson(res, Infraction.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteInfraction(Infraction infraction) {
        if (infraction == null) return false;
        try {
            String res = sendRequest("DELETE", uri + "/infractions/" + infraction.getId());
            return res.contains("deleted");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Infraction updateInfraction(Infraction infraction) {
        if (infraction == null) return null;
        try {
            Gson gson = new Gson();
            String payload = gson.toJson(infraction);
            String res = sendRequestData("PUT", uri + "/infractions/" + infraction.getId(), payload);
            Infraction updatedInfraction = gson.fromJson(res, Infraction.class);
            setTimestampFields(updatedInfraction, res);
            return updatedInfraction;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
