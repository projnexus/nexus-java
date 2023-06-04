package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.datamodels.User;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import com.google.gson.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UserComponent {

    /**
     * Returns the amount of registered users that have a User data model.
     *
     * @return The amount of registered users.
     */
    public static int getAmountOfUsers() {
        NexusRequest request = new NexusRequest(Method.GET, Route.UserRoutes.GET_USER_AMOUNT);
        RequestResponse response = request.execute();
        if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting the amount of users.");
        return new Gson().fromJson(response.getResponse(), JsonObject.class).get("count").getAsInt();
    }

    /**
     * Get all the users that are registered in the database.
     * @return An array of users.
     */
    public static User[] getAllUsers() {
        NexusRequest request = new NexusRequest(Method.GET, Route.UserRoutes.GET_ALL_USERS);
        RequestResponse response = request.execute();
        if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting all users.");
        return new Gson().fromJson(response.getResponse(), User[].class);
    }

    /**
     * Search the database for a user with a given ID or identifiers.
     * @param id
     * @param identifiers
     * @return The user that was found.
     */
    public static User getUser(String id, String[] identifiers) {
        List<User> users = Arrays.stream(getAllUsers()).toList();

        if (id != null) {
            return users.stream()
                    .filter(user -> user.getId().equalsIgnoreCase(id))
                    .findAny()
                    .orElse(null);
        } else if (identifiers != null) {
            return users.stream()
                    .filter(user -> new HashSet<>(Arrays.asList(user.getIdentifiers())).containsAll(Arrays.asList(identifiers)))
                    .findAny()
                    .orElse(null);
        }

        return null; 
    }

    /**
     * Delete a user from the database.
     * @param id The ID of the user.
     * @return If the user was deleted successfully.
     */
    public static boolean deleteUser(String id) {
        NexusRequest request = new NexusRequest(Method.DELETE, Route.UserRoutes.DELETE_USER + id);
        RequestResponse response = request.execute();
        if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while deleting the user.");
        return true;
    }

    //////! Needs testing !//////

    /**
     * Update a user in the database.
     * @param user The user that should be updated.
     * @return The updated user.
     */
    public static User updateUser(User user) {
        NexusRequest request = new NexusRequest(Method.PUT, Route.UserRoutes.UPDATE_USER + user.getId(), new Gson().toJson(user));
        RequestResponse response = request.execute();
        if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while updating the user.");
        return getUser(user.getId(), null);
    }

}
