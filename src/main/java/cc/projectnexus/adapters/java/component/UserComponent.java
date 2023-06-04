package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.api.NexusApi;
import cc.projectnexus.adapters.java.datamodels.User;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.util.Arrays;

public class UserComponent {

    /**
     * Returns the amount of registered users that have a User data model.
     * @return The amount of registered users.
     * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
     */
    public static int getAmountOfUsers() throws TokenNotAuthorizedException {
        try {
            return Integer.parseInt(NexusApi.provideRequest(Method.GET, "", Route.UserRoutes.GET_USER_AMOUNT));
        } catch (TokenNotAuthorizedException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Get all the users that are registered in the database.
     * @return An array of users.
     * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
     */
    public static User[] getAllUsers() {
        try {
            System.out.println(NexusApi.provideRequest(Method.GET, "", Route.UserRoutes.GET_ALL_USERS));
        } catch (TokenNotAuthorizedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Search the database for a user with a given ID or identifiers.
     * @param id
     * @param identifiers
     * @return The user that was found.
     * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
     */
    public static User getUser(String id, String[] identifiers) {
        try {
            if (id == null) {
                JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
                for (String identifier : identifiers) {
                    jsonArrayBuilder.add(identifier);
                }

                JsonArray jsonArray = jsonArrayBuilder.build();

                JsonObject jsonObject = Json.createObjectBuilder()
                        .add("identifiers", jsonArray)
                        .build();

                System.out.println(NexusApi.provideRequest(Method.GET, jsonObject.toString(), Route.UserRoutes.GET_DATA_SINGLE));
            } else {
                JsonObject jsonObject = Json.createObjectBuilder()
                        .add("id", id)
                        .build();

                System.out.println(NexusApi.provideRequest(Method.GET, jsonObject.toString(), Route.UserRoutes.GET_DATA_SINGLE));
            }
        } catch (TokenNotAuthorizedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
