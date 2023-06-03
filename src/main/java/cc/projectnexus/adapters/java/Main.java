package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.component.GuildComponent;
import cc.projectnexus.adapters.java.datamodels.GuildSettings;
import cc.projectnexus.adapters.java.datamodels.Region;
import cc.projectnexus.adapters.java.exceptions.TokenNotAuthorizedException;

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
		GuildComponent.placeGuildSettings(publish);
	}

	@Override
	public void authFailed() {

	}
}
