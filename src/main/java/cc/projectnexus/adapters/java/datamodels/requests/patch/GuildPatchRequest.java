package cc.projectnexus.adapters.java.datamodels.requests.patch;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class GuildPatchRequest {
	@JsonbProperty("auto_ban")
	private boolean auto_ban;

	@JsonbProperty("auto_unban")
	private boolean auto_unban;

	@JsonbProperty("logs_channel")
	private String logs_channel;

	@JsonbProperty("enabled_regions")
	private String[] enabled_regions;

	public static GuildPatchRequest from(boolean auto_ban, boolean auto_unban, String logs_channel, String[] enabledRegions) {
		GuildPatchRequest request = new GuildPatchRequest();
		request.setAuto_ban(auto_ban);
		request.setAuto_unban(auto_unban);
		request.setLogs_channel(logs_channel);
		request.setEnabled_regions(enabledRegions);
		return request;
	}
}
