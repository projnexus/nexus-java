package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.NexusApi;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;

import java.io.IOException;

public class AuthorizeComponent {

	private static String BASE_API = "https://api.projectnexus.cc/";

	/**
	 * Authorize the token in the API.
	 * @param token The token to authorize.
	 * @return Rather or not the authorization was completed.
	 * @see cc.projectnexus.adapters.java.NexusClient instead of handling your authorization yourself.
	 */
	public static boolean authorizeToken(String token) {
		try {
			return NexusApi.attempt(token); // TODO: Implement
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
