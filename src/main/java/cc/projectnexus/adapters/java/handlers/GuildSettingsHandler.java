package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.api.ApiInteraction;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GuildSettingsHandler {
	public static NexusClient getClient() {
		return NexusClient.getNexusInstance();
	}

	/**
	 * Get a list over all guild settings.
	 * @return A list over all guild settings that is created and valid.
	 * @throws IllegalAccessException If the client is not set
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
	 * @throws IllegalAccessException If the client is not set
	 */
	public static Optional<GuildSettings> getGuildSettingsFromGuild(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getGuildSettingsByFilter(g -> Objects.equals(g.getGuildId(), id)).stream().findAny();
	}

	/**
	 * Get guild settings based on a filter predicate.
	 * @param infractionPredicate The predicate filter
	 * @return Return a list of List#GuildSettings of matching the filter.
	 * @throws IllegalAccessException If the client is not set
	 */
	public static List<GuildSettings> getGuildSettingsByFilter(Predicate<GuildSettings> infractionPredicate) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getAllGuildSettings().stream().filter(infractionPredicate).collect(Collectors.toList());
	}

	/**
	 * Create a guild settings from the {@link ApiInteraction} createGuild method.
	 * @param id The guild identifier
	 * @return Return a new created Guild Settings object.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static GuildSettings createGuildSettings(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.createGuild(id);
	}

	/**
	 * Update the Guild Settings object.
	 * @param guildSettings The guild settings
	 * @return An updated GuildSettings object.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static GuildSettings updateGuildSettings(GuildSettings guildSettings) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.updateGuild(guildSettings);
	}

	/**
	 * Delete guild settings from Guild ID.
	 * @return If operation was successful or not.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static boolean deleteGuildSettings(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		if (getGuildSettingsFromGuild(id).isEmpty()) {
			return ApiInteraction.deleteGuild(getGuildSettingsFromGuild(id).get());
		}
		return false;
	}
}
