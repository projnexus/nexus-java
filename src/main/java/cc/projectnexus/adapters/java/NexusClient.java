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

    public NexusClient(String token, NexusClientProperties properties) {
        System.out.println("Attempting to authorize provided token: " + token);
        this.apiKey = token;
        this.apiUri = "https://projectnexus.cc/api";
        NexusHandler.setClient(this);

        isAuthorized = ApiInteraction.test();

        if (isAuthorized || !properties.isUseTokenAuthorize()) {
            if (properties.isDebug()) {
                boolean test = ApiInteraction.test();
                if (test) {
                    onAuthorizeSuccess();
                    System.out.println("Authorized Nexus Client.");
                } else {
                    onAuthorizeFail();
                    System.out.println("Could not reach Nexus Client after authorization...");
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