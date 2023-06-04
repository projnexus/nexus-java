package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.datamodels.User;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import com.google.gson.*;

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
        if (id == null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("identifiers", new Gson().toJsonTree(identifiers));
            NexusRequest request = new NexusRequest(Method.GET, Route.UserRoutes.GET_DATA_SINGLE, jsonObject.toString());
            RequestResponse response = request.execute();
            if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting the user.");
            return new Gson().fromJson(response.getResponse(), User.class);
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", id);
            NexusRequest request = new NexusRequest(Method.GET, Route.UserRoutes.GET_DATA_SINGLE, jsonObject.toString());
            RequestResponse response = request.execute();
            System.out.println(response.getResponse());
            if (response.getResponseCode() != 200) throw new RuntimeException("Something went wrong while getting the user.");
            return new Gson().fromJson(response.getResponse(), User.class);
        }
    }

}
