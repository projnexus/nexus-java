package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.datamodels.User;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import com.google.gson.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class UserComponent {

    /**
     * Returns the amount of registered users that have a User data model.
     *
     * @return The amount of registered users.
     * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
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
     * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
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
     * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
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

}
