package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class GuildComponent {

	/**
	 * Returns the amount of registered guilds that have a Guild data model.
	 *
	 * @return The amount of registered guilds.
	 */
	public static int getAmountOfGuilds() {
		NexusRequest request = new NexusRequest(Method.GET, Route.GuildsRoutes.GET_AMOUNT_COUNT);
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting the amount of guilds.");
		return new Gson().fromJson(response.getResponse(), JsonObject.class).get("count").getAsInt();
	}

	/**
	 * Search the database for a guilds with a given ID
	 * @param id The guild's Discord ID
	 * @return The guilds that was found.
	 */
	public static GuildSettings getGuild (String id) {
		NexusRequest request = new NexusRequest(Method.GET, Route.GuildsRoutes.GET_DATA_SINGLE + id);
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting the guild.");
		return new Gson().fromJson(response.getResponse(), GuildSettings.class);
	}

	/**
	 * Create a new guild in the database.
	 * @param data The data to pass
	 * @return The created guild.
	 */
	public static GuildSettings createGuild(JsonObject data) {
		NexusRequest request = new NexusRequest(Method.POST, Route.GuildsRoutes.POST_CREATE_NEW, data.toString());
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 201) throw new RuntimeException("Something went wrong while creating the guild.");
		return new Gson().fromJson(response.getResponse(), GuildSettings.class);
	}

	/**
	 * Delete a guild from the database.
	 * @param id The ID of the guild.
	 * @return If the guild was deleted successfully.
	 */
	public static boolean deleteGuild(String id) {
		NexusRequest request = new NexusRequest(Method.DELETE, Route.GuildsRoutes.DELETE_GUILD + id);
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while deleting the guild.");
		return true;
	}

	//////! Needs testing !//////

	/**
	 * Update a guild in the database
	 * @param guild The guild to update.
	 * @return The updated guild.
	 */
	public static GuildSettings updateGuild(GuildSettings guild) {
		JsonObject data = new JsonObject();
		data.addProperty("auto_ban", guild.isAutoBan());
		data.addProperty("auto_unban", guild.isAutoUnban());
		data.addProperty("logs_channel", guild.getLogsChannel());
		data.add("enabled_regions", new Gson().toJsonTree(guild.getEnabledRegions()));
		NexusRequest request = new NexusRequest(Method.PUT, Route.GuildsRoutes.PUT_UPDATE_GUILD + guild.getGuildId(), data.toString());
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while updating the guild.");
		return new Gson().fromJson(response.getResponse(), GuildSettings.class);
	}

}