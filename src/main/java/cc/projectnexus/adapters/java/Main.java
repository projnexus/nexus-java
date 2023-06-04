package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Region;
import cc.projectnexus.adapters.java.route.Method;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbConfig;
import org.eclipse.yasson.JsonBindingProvider;

import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

public class Main extends NexusClient {
	private String token = "BOT 2OAln4KjAoeO9KfFPaEcijZ2YsBotqccnGEMz18H2g6p175Cevm2Ujp0DOzwab6L";

	public Main() {
		super(true, new NexusClientProperties("BOT 2OAln4KjAoeO9KfFPaEcijZ2YsBotqccnGEMz18H2g6p175Cevm2Ujp0DOzwab6L", true));

	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void authSuccess() {
		GuildSettings publish = new GuildSettings("1078849224067776552", "1078849224067776552", true, true, "", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()), new Region[]{Region.EUROPE});

		JsonBindingProvider provider = new JsonBindingProvider();
		jakarta.json.bind.JsonbConfig config = new JsonbConfig();
		Jsonb jsonb = provider.create().withConfig(config).build();

		String json = jsonb.toJson(publish, GuildSettings.class);

		GuildSettings settings = jsonb.fromJson(json, GuildSettings.class);

		NexusRequest request = new NexusRequest(Method.POST, "https://api.projectnexus.cc/guild", json);
		RequestResponse response = request.execute();
		System.out.println(request.getData());
	}

	@Override
	public void authFailed() {

	}
}
