package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.component.GuildComponent;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Region;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;
import cc.projectnexus.adapters.java.route.Route;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		ObjectMapper mapper = new ObjectMapper();
		String guildJson;
		try {
			guildJson = mapper.writeValueAsString(publish);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		try {
			NexusRequester.sendPostRequest("https://api.projectnexus.cc/guild", guildJson, getInstance().getProperties().getToken());
			System.out.println(guildJson);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void authFailed() {

	}
}
