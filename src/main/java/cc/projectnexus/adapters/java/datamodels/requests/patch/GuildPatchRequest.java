package cc.projectnexus.adapters.java.datamodels.requests.patch;

import lombok.Data;

@Data
public class GuildPatchRequest {
	private boolean auto_ban;
	private boolean auto_unban;
	private String logs_channel;
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
