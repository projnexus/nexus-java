package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.handlers.NexusHandler;
import cc.projectnexus.adapters.java.api.ApiInteraction;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NexusClient {
    private boolean isAuthorized;
    private String apiKey;
    private String apiUri;

    private NexusClientProperties properties;

    public NexusClient(String token, NexusClientProperties properties) {
        this.properties = properties;
        System.out.println("Attempting to authorize provided token: " + token);
        this.apiKey = token;
        this.apiUri = "https://projectnexus.cc/api";
        isAuthorized = ApiInteraction.test();

        if (isAuthorized || !properties.isUseTokenAuthorize()) {
            if (properties.isDebug()) {
                boolean test = ApiInteraction.test();
                if (test) {
                    onAuthorizeSuccess();
                    if (properties.isDebug()) {
                        System.out.println("Connection to API successful.");
                    }
                } else {
                    onAuthorizeFail();
                    if (properties.isDebug()) {
                        System.out.println("Connection to API Failed.");
                    }
                }

                if (!properties.isUseTokenAuthorize()) {
                    onAuthorizeSuccess();
                }
            }
            boolean test = ApiInteraction.test();
            if (test) {
                onAuthorizeFail();
            } else {
                onAuthorizeFail();
            }
        } else {
            onAuthorizeFail();
            if (properties.isDebug()) {
                System.out.println("Could not authorize NexusClient.");
            }
        }
    }
    public abstract void onAuthorizeSuccess();
    public abstract void onAuthorizeFail();

}