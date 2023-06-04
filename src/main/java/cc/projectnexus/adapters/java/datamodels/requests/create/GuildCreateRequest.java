
package cc.projectnexus.adapters.java.datamodels.requests.create;

import lombok.Data;

@Data
public class GuildCreateRequest {
	private String id;

	public static GuildCreateRequest from(String id) {
		GuildCreateRequest request = new GuildCreateRequest();
		request.setId(id);
		return request;
	}
}
