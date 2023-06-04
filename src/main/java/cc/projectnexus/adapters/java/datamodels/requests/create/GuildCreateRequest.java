
package cc.projectnexus.adapters.java.datamodels.requests.create;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class GuildCreateRequest {
	@JsonbProperty("id")
	private String id;

	public static GuildCreateRequest from(String id) {
		GuildCreateRequest request = new GuildCreateRequest();
		request.setId(id);
		return request;
	}
}
