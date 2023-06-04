package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.adapter.JsonAdapter;
import cc.projectnexus.adapters.java.client.NexusClient;
import cc.projectnexus.adapters.java.client.NexusClientProperties;
import cc.projectnexus.adapters.java.component.GuildComponent;
import cc.projectnexus.adapters.java.component.InfractionComponent;
import cc.projectnexus.adapters.java.component.UserComponent;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Infraction;
import cc.projectnexus.adapters.java.datamodels.Region;
import cc.projectnexus.adapters.java.datamodels.requests.create.InfractionCreateRequest;
import cc.projectnexus.adapters.java.request.NexusRequest;
import cc.projectnexus.adapters.java.request.RequestResponse;
import cc.projectnexus.adapters.java.route.Method;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbConfig;
import org.eclipse.yasson.JsonBindingProvider;

import java.sql.Array;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

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
		System.out.println(UserComponent.getUser("eb18aac9-7cd0-483e-8c91-939eaba34e98", null));
	}

	@Override
	public void authFailed() {

	}
}
