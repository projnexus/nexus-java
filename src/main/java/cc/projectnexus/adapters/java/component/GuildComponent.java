package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.api.NexusApi;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.route.Method;
import cc.projectnexus.adapters.java.route.Route;

public class GuildComponent {

	/**
	 * Returns the amount of registered guilds that has a Guild-Settings data model.
	 * @return The amount of registered guilds.
	 * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
 	 */
	public static int getAmountOfGuilds() throws TokenNotAuthorizedException {
		try {
			return Integer.parseInt(NexusApi.provideRequest(Method.GET, "", Route.GuildsRoutes.GET_AMOUNT_COUNT));
		} catch (TokenNotAuthorizedException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public static GuildSettings getGuild(String id) {
		try {
			System.out.println(NexusApi.provideRequest(Method.GET, "", Route.GuildsRoutes.GET_DATA_SINGLE + id));
		} catch (TokenNotAuthorizedException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public static void placeGuildSettings(GuildSettings settings) {

	}

}
