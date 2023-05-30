package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;

public class NexusHandler {
    private static NexusClient client;
    private static boolean isAuthorized = client.isAuthorized();

    public static void setClient(NexusClient client) {
        NexusHandler.client = client;
    }

    public static NexusClient getClient() {
        return client;
    }
}