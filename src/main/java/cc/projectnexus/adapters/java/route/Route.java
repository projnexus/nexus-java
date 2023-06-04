package cc.projectnexus.adapters.java.route;

import cc.projectnexus.adapters.java.api.NexusApi;

/**
 * All supported routes by Nexus Project.
 */
public class Route {
	public static String API_URL = NexusApi.API_URL;

	public static class UserRoutes {
		public static String GET_USER_AMOUNT = API_URL + "users/count";
		public static String GET_ALL_USERS = API_URL + "users";
		public static String GET_DATA_SINGLE = API_URL + "user";
		public static String DELETE_USER = API_URL + "user";
		public static String UPDATE_USER = API_URL + "user";
	}

	public static class InfractionRoutes {
		public static String GET_INFRACTION_COUNT = API_URL + "infractions/count";
		public static String GET_ALL_INFRACTIONS = API_URL + "infractions";
		public static String GET_DATA_SINGLE = API_URL + "infractions/infraction/";
		public static String POST_NEW_INFRACTION = API_URL + "infraction/";
		public static String DELETE_INFRACTION = API_URL + "infraction/";
		public static String PUT_UPDATE_INFRACTION = API_URL + "infraction/";
	}

	public static class AdminRoutes {
		public static String POST_CREATE_KEY = API_URL + "admin/key";
		public static String GET_KEY_DATA = API_URL + "admin/key/";
		public static String DELETE_KEY = API_URL + "admin/key/";
		public static String UPDATE_KEY = API_URL + "admin/key/";
	}

	public static class GuildsRoutes {
		public static String GET_AMOUNT_COUNT = API_URL + "guilds/count";
		public static String GET_DATA_SINGLE = API_URL + "guilds/";
		public static String POST_CREATE_NEW = API_URL + "guild/";
		public static String DELETE_GUILD = API_URL + "guild/";
		public static String PUT_UPDATE_GUILD = API_URL + "guild/";
	}
}