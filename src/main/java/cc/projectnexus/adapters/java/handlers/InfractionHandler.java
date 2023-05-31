package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.api.ApiInteraction;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InfractionHandler {

	public static NexusClient getClient() {
		return NexusClient.getNexusInstance();
	}

	/**
	 * Get a list of Infractions from the database.
	 * @return A new list of Guild Settings
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static List<Infraction> getAllInfractions() throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return Arrays.asList(ApiInteraction.getAllInfractions());
	}

	/**
	 * A list of Infractions from a User ID.
	 * @param id The user identifier you want to read.
	 * @return A list of Infractions from the User.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static List<Infraction> getInfractionsFromUser(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getAllInfractions().stream().filter(inf -> inf.getUserId() == Long.valueOf(id)).collect(Collectors.toList());
	}

	/**
	 * A list of Infractions from a Region.
	 * @param region The region enum
	 * @return A list of Infractions from the Region.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static List<Infraction> getInfractionsFromRegion(Region region) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}

		List<Infraction> found = new ArrayList<>();
		for (Infraction interaction : getAllInfractions()) {
			for (Region re : interaction.getRegions()) {
				if (region == re) {
					found.add(interaction);
				}
			}
		}

		return found;
	}

	/**
	 * A list of Infractions from a filter predicate.
	 * @param infractionPredicate The predicate that is the filter.
	 * @return A list of Infractions from the predicate.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static List<Infraction> getInfractionByFilter(Predicate<Infraction> infractionPredicate) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getAllInfractions().stream().filter(infractionPredicate).collect(Collectors.toList());
	}

	/**
	 * A list of Infractions from an ID.
	 * @param id The ID that you want to get from.
	 * @return Infraction from Infractions ID.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static Optional<Infraction> getInfractionById(long id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return Optional.ofNullable(ApiInteraction.getInfraction(id));
	}

	/**
	 * Create a new infraction
 	 * @param infraction The infraction data model you want to create from
	 * @return A new created data model.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static Infraction createInfraction(Infraction infraction) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.createInfraction(infraction);
	}

	/**
	 * Update a new infraction.
	 * @param infraction The infraction data model you want to update from.
	 * @return A new updated data model.
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static Infraction updateInfraction(Infraction infraction) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.updateInfraction(infraction);
	}

	/**
	 * Delete a infraction.
	 * @param infraction The infraction data model you want to delete
	 * @return The success operation (boolean).
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static boolean deleteInfraction(Infraction infraction) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.deleteInfraction(infraction);
	}

	/**
	 * Remember that you only get ONE returned object and not a list as you normally would get.
	 * @param filter The predicate filter.
	 * @return The success operation (boolean).
	 * @throws IllegalAccessException If the client is not set.
	 */
	public static boolean deleteInfractionByFilter(Predicate<Infraction> filter) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}

		Infraction infraction = getInfractionByFilter(filter)
				.stream()
				.findAny()
				.get();

		return ApiInteraction.deleteInfraction(infraction);
	}
}
