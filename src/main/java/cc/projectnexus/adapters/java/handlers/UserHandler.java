package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.api.ApiInteraction;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;
import cc.projectnexus.adapters.java.datamodels.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserHandler {

    public static NexusClient getClient() {
        return NexusClient.getNexusInstance();
    }

    /**
     * Get a list of Users from the database.
     * @return A new list of Users
     * @throws IllegalAccessException If the client is not set.
     */
    public static List<User> getAllUsers() throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        return Arrays.asList(Objects.requireNonNull(ApiInteraction.getAllUsers()));
    }

    /**
     * Get a User by their database identifier.
     * @param id The user identifier you want to read.
     * @return An object of the user's data.
     * @throws IllegalAccessException If the client is not set.
     */
    public static User getUser(String id) throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        return ApiInteraction.getUser(id);
    }

    /**
     * Delete a user by an instance of the User class.
     * @param user The user you want to delete.
     * @return A boolean.
     * @throws IllegalAccessException If the client is not set.
     */
    public static boolean deleteUser(User user) throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        return ApiInteraction.deleteUser(user);
    }
}
