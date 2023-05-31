package cc.projectnexus.adapters.java.utils;

import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class JavaScriptUtils {

	public static Timestamp timestampJsToJava(String jsTimestamp) {
		DateTimeFormatter jsFormatter = DateTimeFormatter.ISO_INSTANT;

		try {
			Instant instant = Instant.from(jsFormatter.parse(jsTimestamp));
			LocalDateTime javaDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			return Timestamp.valueOf(javaDateTime);
		} catch (Exception e) {
			e.printStackTrace();
			return null; // or handle the error as needed
		}
	}

	public static void setTimestampFields(GuildSettings guildSettings, String response) {
		JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);

		String createdAt = responseJson.get("createdAt").getAsString();
		String updatedAt = responseJson.get("lastUpdated").getAsString();

		guildSettings.setCreatedAt(timestampJsToJava(createdAt));
		guildSettings.setUpdatedAt(timestampJsToJava(updatedAt));
	}

	public static void setTimestampFields(Infraction infraction, String response) {
		JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);

		String createdAt = responseJson.get("createdAt").getAsString();
		String updatedAt = responseJson.get("lastUpdated").getAsString();

		infraction.setCreatedAt(timestampJsToJava(createdAt));
		infraction.setUpdatedAt(timestampJsToJava(updatedAt));
	}

	public static void setTimestampFields(User user, String response) {
		JsonObject responseJson = new Gson().fromJson(response, JsonObject.class);

		String createdAt = responseJson.get("createdAt").getAsString();
		String updatedAt = responseJson.get("updatedAt").getAsString();

		user.setCreatedAt(timestampJsToJava(createdAt));
		user.setUpdatedAt(timestampJsToJava(updatedAt));
	}

}
