package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.api.ApiInteraction;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InfractionHandler {
	public static NexusClient getClient() {
		return NexusHandler.getClient();
	}

	public static List<Infraction> getAllInfractions() throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return Arrays.asList(ApiInteraction.getAllInfractions());
	}

	public static List<Infraction> getInfractionsFromUser(String id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getAllInfractions().stream().filter(inf -> inf.getUserId() == Long.valueOf(id)).collect(Collectors.toList());
	}

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

	public static List<Infraction> getInfractionByFilter(Predicate<Infraction> infractionPredicate) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return getAllInfractions().stream().filter(infractionPredicate).collect(Collectors.toList());
	}

	public static Infraction getInfractionById(long id) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.getInfraction(id);
	}

	public static Infraction createInfraction(Infraction infraction) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.createInfraction(infraction);
	}

	public static Infraction updateInfraction(Infraction infraction) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.updateInfraction(infraction);
	}

	public static boolean deleteInfraction(Infraction infraction) throws IllegalAccessException {
		if (getClient() == null) {
			throw new IllegalAccessException("You must set the client before accessing methods.");
		}
		return ApiInteraction.deleteInfraction(infraction);
	}
}
