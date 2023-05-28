package cc.projectnexus.adapters.java;

public class NexusHandler {
    private static NexusClient client;
    private static boolean isAuthorized = client.isAuthorized();

    public static void setClient(NexusClient client) {
        NexusHandler.client = client;
    }

    public static NexusClient getClient() {
        return client;
    }

    public static Infraction getAllInfractions() throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        // TODO: Accept method
        return null;
    }

    public static Infraction getInfractionFromUser(String id) throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        // TODO: Accept method
        return null;
    }

    public static Infraction getInfractionFromRegion(String regionName) throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        return null;
    }


}
