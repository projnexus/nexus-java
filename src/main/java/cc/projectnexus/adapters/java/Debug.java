package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.client.NexusClient;
import cc.projectnexus.adapters.java.client.NexusClientProperties;
import cc.projectnexus.adapters.java.component.GuildComponent;
import cc.projectnexus.adapters.java.component.InfractionComponent;

import java.util.Arrays;
import java.util.List;

import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;
import com.google.gson.*;

public class Debug extends NexusClient {
	private String token = "BOT 2OAln4KjAoeO9KfFPaEcijZ2YsBotqccnGEMz18H2g6p175Cevm2Ujp0DOzwab6L";

	public Debug() {
		super(true, new NexusClientProperties("BOT 2OAln4KjAoeO9KfFPaEcijZ2YsBotqccnGEMz18H2g6p175Cevm2Ujp0DOzwab6L", true));

	}

	public static void main(String[] args) {
		new Debug();
	}

	@Override
	public void authSuccess() {
//		// create a new object with gson
//		JsonObject object = new JsonObject();
//		object.addProperty("reason", "test");
//		object.addProperty("proof", "test");
//		object.addProperty("executor", 1);
//
//		// create a new array of region
//		JsonArray regions = new JsonArray();
//		regions.add("europe");
//		regions.add("na");
//
//		// create a new array of identifiers
//		JsonArray identifiers = new JsonArray();
//		identifiers.add("steam:123456789");
//
//		// add the regions and identifiers to the object
//		object.add("regions", regions);
//		object.add("identifiers", identifiers);

		// create a new infraction
//		JsonObject guildSettings = new JsonObject();
//		guildSettings.addProperty("id", "1");


	}

	@Override
	public void authFailed() {

	}
}
