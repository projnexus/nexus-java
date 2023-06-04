package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.adapter.JsonAdapter;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.requests.create.GuildCreateRequest;
import cc.projectnexus.adapters.java.datamodels.requests.patch.GuildPatchRequest;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import cc.projectnexus.adapters.java.route.Method;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GuildComponent {

	/**
	 * Returns the amount of registered guilds that has a Guild-Settings data model.
	 * @return The amount of registered guilds.
	 * @throws TokenNotAuthorizedException If the token is not properly authorized, or is missing something.
 	 */
	public static int getGuildAmount() {
		NexusRequest request = new NexusRequest(Method.GET, "https://api.projectnexus.cc/guilds/count");
		RequestResponse response = request.execute();
		JsonAdapter jsonAdapter = new JsonAdapter();

		if (response.getResponse().isEmpty()) {
			return 0;
		} else {
			String json = response.getResponse();
			Gson gson = new Gson();

			try {
				JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				int amount = jsonObject.get("count").getAsInt();

				return amount;
			} catch (Exception e) {
				return 0;
			}
		}
	}

	public static GuildSettings getGuild(String id) {
		NexusRequest request = new NexusRequest(Method.GET, "https://api.projectnexus.cc/guild/" + id);
		RequestResponse res = request.execute();

		JsonAdapter jsonAdapter = new JsonAdapter();

		GuildSettings guildSettings = jsonAdapter.fromJson(res.getResponse(), GuildSettings.class);

		if (guildSettings != null) {
			return guildSettings;
		} else {
			return null;
		}
	}

	public static RequestResponse addGuildSettings(GuildCreateRequest req) {
		JsonAdapter adapter = new JsonAdapter();
		String json = adapter.toJson(req);

		NexusRequest request = new NexusRequest(Method.POST, "https://api.projectnexus.cc/guild", json);
		RequestResponse res = request.execute();

		return res;
	}

	public static RequestResponse deleteGuild(String guildId) {
		NexusRequest request = new NexusRequest(Method.DELETE, "https://api.projectnexus.cc/guild/" + guildId);
		RequestResponse res = request.execute();
		return res;
	}

	public static RequestResponse patchGuild(String id, GuildPatchRequest req) {
		JsonAdapter adapter = new JsonAdapter();
		String json = adapter.toJson(req);

		NexusRequest request = new NexusRequest(Method.PATCH, "https://api.projectnexus.cc/guild/" + id, json);
		RequestResponse res = request.execute();

		return res;
	}

}