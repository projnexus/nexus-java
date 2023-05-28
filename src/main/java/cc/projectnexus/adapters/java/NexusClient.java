package cc.projectnexus.adapters.java;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class NexusClient {
    private boolean isAuthorized;

    private String apiKey;

    public NexusClient(String token) {
        System.out.println("Attempting to authorize provided token: " + token);
        this.apiKey = token;

        // TODO: Add a return message if authorized
        isAuthorized = false;

        if (isAuthorized) {
            onEnable();
        }
    }

    public abstract void onEnable();



}