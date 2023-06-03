package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.NexusApi;
import cc.projectnexus.adapters.java.NexusClient;

import java.io.IOException;

/**
 * Handle the authorization of API keys.
 */
public class AuthorizeComponent {

	/**
	 * Authorize the token in the API.
	 * @return Rather or not the authorization was completed.
	 * @see cc.projectnexus.adapters.java.NexusClient instead of handling your authorization yourself.
	 */
	public static boolean authorizeToken() {
		final String token = NexusClient.getInstance().getProperties().getToken();
		try {
			return NexusApi.attempt(token);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
