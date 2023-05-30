package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.api.ApiInteraction;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GuildSettingsHandler {
	public static NexusClient getClient() {
		return NexusHandler.getClient();
	}

	/**
	 * Get a list over all guild settings.
	 * @return A list over all guild settings that is created and valid.
	 * @throws IllegalAccessException If the client is not set in NexusHandler#setClient()
	 */
	public static List<GuildSettings> getAllGuildSettings() throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return Arrays.asList(ApiInteraction.getAllGuilds());
	}

	/**
	 * Get guild settings object based on Guild ID.
	 * @return Return any guild setting from ID or null. Only returns one object meaning that if there are duplicates in db you will only get one.
	 * @throws IllegalAccessException If the client is not set in NexusHandler#setClient()
	 */
	public static GuildSettings getGuildSettingsFromGuild(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getGuildSettingsByFilter(g -> g.getGuildId() == id).stream().findAny().get();
	}

	public static List<GuildSettings> getGuildSettingsByFilter(Predicate<GuildSettings> infractionPredicate) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getAllGuildSettings().stream().filter(infractionPredicate).collect(Collectors.toList());
	}

	public static GuildSettings createGuildSettings(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.createGuild(id);
	}

	public static GuildSettings updateGuildSettings(GuildSettings guildSettings) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.updateGuild(guildSettings);
	}

	public static boolean deleteGuildSettings(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.deleteGuild(getGuildSettingsFromGuild(id));
	}
}
