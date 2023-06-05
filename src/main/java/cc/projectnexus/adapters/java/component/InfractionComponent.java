package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class InfractionComponent {

	/**
	 * Returns the amount of registered infractions that have an Infraction data model.
	 *
	 * @return The amount of registered infractions.
	 */
	public static int getAmountOfInfractions() {
		NexusRequest request = new NexusRequest(Method.GET, Route.InfractionRoutes.GET_INFRACTION_COUNT);
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting the amount of infractions." +
				"Received Code: " + response.getResponseCode());
		return new Gson().fromJson(response.getResponse(), JsonObject.class).get("count").getAsInt();
	}

	/**
	 * Get all the infractions that are registered in the database.
	 * @return An array of infractions.
	 */
	public static Infraction[] getAllInfractions() {
		NexusRequest request = new NexusRequest(Method.GET, Route.InfractionRoutes.GET_ALL_INFRACTIONS);
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting all infractions." +
				"Received Code: " + response.getResponseCode());
		return new Gson().fromJson(response.getResponse(), Infraction[].class);
	}

	/**
	 * Search the database for an infraction with a given ID or User ID.
	 * @param id
	 * @param userId
	 * @return The infraction that was found.
	 */
	public static Infraction getInfraction (String id, String userId) {
		List<Infraction> infractions = Arrays.stream(getAllInfractions()).toList();

		if (id != null) {
			return infractions.stream()
					.filter(infraction -> infraction.getId().equalsIgnoreCase(id))
					.findAny()
					.orElse(null);
		} else if (userId != null) {
			return infractions.stream()
					.filter(infraction -> infraction.getUserId().equalsIgnoreCase(userId))
					.findAny()
					.orElse(null);
		}

		return null;
	}

	/**
	 * Create a new infraction in the database.
	 * @param data The data to pass
	 * @return The created infraction.
	 */
	public static Infraction createInfraction(JsonObject data) {
		NexusRequest request = new NexusRequest(Method.POST, Route.InfractionRoutes.POST_NEW_INFRACTION, data.toString());
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 201) throw new RuntimeException("Something went wrong while creating a new infraction. " +
				"Received Code: " + response.getResponseCode());
		return new Gson().fromJson(response.getResponse(), Infraction.class);
	}

	/**
	 * Delete an infraction from the database.
	 * @param id The ID of the infraction.
	 * @return If the infraction was deleted successfully.
	 */
	public static boolean deleteInfraction(String id) {
		NexusRequest request = new NexusRequest(Method.DELETE, Route.InfractionRoutes.DELETE_INFRACTION + id);
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while deleting the infraction." +
				"Received-Code: " + response.getResponseCode());
		return true;
	}

	//////! Needs testing !//////

	/**
	 * Update an infraction in the database
	 * @param infraction The infraction to update.
	 * @return The updated infraction.
	 */
	public static Infraction updateInfraction(Infraction infraction) {
		NexusRequest request = new NexusRequest(Method.PUT, Route.InfractionRoutes.PUT_UPDATE_INFRACTION + infraction.getId(), new Gson().toJson(infraction));
		RequestResponse response = request.execute();
		if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while updating the infraction. " +
				"Received Code: " + response.getResponseCode());
		return new Gson().fromJson(response.getResponse(), Infraction.class);
	}
}
