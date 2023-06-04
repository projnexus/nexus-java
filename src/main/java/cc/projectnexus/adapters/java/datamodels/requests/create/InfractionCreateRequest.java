package cc.projectnexus.adapters.java.datamodels.requests.create;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class InfractionCreateRequest {
	@JsonbProperty("identifiers")
	private String[] identifiers;

	@JsonbProperty("regions")
	private String[] regions;

	@JsonbProperty("reason")
	private String reason;

	@JsonbProperty("proof")
	private String proof;

	@JsonbProperty("executor")
	private long executor;

	public static InfractionCreateRequest from(String[] identifiers, String[] regions, String reason, String proof, long executor) {
		InfractionCreateRequest request = new InfractionCreateRequest();
		request.setIdentifiers(identifiers);
		request.setRegions(regions);
		request.setReason(reason);
		request.setProof(proof);
		request.setExecutor(executor);
		return new InfractionCreateRequest();
	}

	public static InfractionCreateRequest from(String id, String[] regions, String reason, String proof, long executor) {
		return from(new String[]{id}, regions, reason, proof, executor);
	}
}
