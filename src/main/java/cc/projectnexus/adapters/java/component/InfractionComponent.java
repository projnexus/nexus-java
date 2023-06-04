package cc.projectnexus.adapters.java.component;

import cc.projectnexus.adapters.java.adapter.JsonAdapter;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import cc.projectnexus.adapters.java.route.Method;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class InfractionComponent {

	public static int getInfractionAmount() {
		NexusRequest request = new NexusRequest(Method.GET, "https://api.projectnexus.cc/infractions/count");
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

	public static List<Infraction> getAllInfractions() {
		JsonAdapter jsonAdapter = new JsonAdapter();

		NexusRequest request = new NexusRequest(
				Method.GET,
				"https://api.projectnexus.cc/infractions"
		);

		RequestResponse res = request.execute();

		Infraction[] infractionArray = jsonAdapter.fromJson(res.getResponse(), Infraction[].class);
		List<Infraction> infractionList = Arrays.asList(infractionArray);

		infractionList.forEach(g -> {
			System.out.println(g.getInfractionReason());
		});

		return infractionList;
	}

	public static RequestResponse addInfraction(Infraction infraction) {
		JsonAdapter jsonAdapter = new JsonAdapter();
		String json = jsonAdapter.toJson(infraction);

		System.out.println(json);

		NexusRequest request = new NexusRequest(Method.POST, "https://api.projectnexus.cc/infraction", json);
		RequestResponse res = request.execute();

		return res;
	}

}
