package cc.projectnexus.adapters.java.handlers;

import cc.projectnexus.adapters.java.NexusClient;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NexusHandler {
    private static NexusClient client;
    private static boolean isAuthorized = client.isAuthorized();

    public static void setClient(NexusClient client) {
        NexusHandler.client = client;
    }

    public static NexusClient getClient() {
        return client;
    }

    public static List<Infraction> getAllInfractions() throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }

        try {
            String res = NexusHttpHandler.sendRequest("GET", getClient().getApiKey() + "/infractions");
            JSONObject jsonRes = new JSONObject(res);
            JSONArray array = jsonRes.getJSONArray("infractions");

            ArrayList<Infraction> infractions = new ArrayList<>();
            array.forEach(a -> {
                JSONObject object = new JSONObject(a);
                Infraction infraction = new Infraction(
                        object.getLong("id"),
                        object.getLong("user_id"),
                        (String[]) object.get("user_data"),

                )
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public static Infraction getInfractionsFromUser(String id) throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        // TODO: Accept method
        return null;
    }

    public static List<Infraction> getInfractionsFromRegion(Region region) throws IllegalAccessException {
        if (getClient() == null) {
            throw new IllegalAccessException("You must set the client before accessing methods.");
        }
        return null;
    }

}
