package cc.projectnexus.adapters.java.datamodels.requests.create;

import lombok.Data;

@Data
public class InfractionCreateRequest {
	private String[] identifiers;
	private String[] regions;
	private String reason;
	private String proof;
	private String executor;

	public static InfractionCreateRequest from(String[] identifiers, String[] regions, String reason, String proof, String executor) {
		InfractionCreateRequest request = new InfractionCreateRequest();
		request.setIdentifiers(identifiers);
		request.setRegions(regions);
		request.setReason(reason);
		request.setProof(proof);
		request.setExecutor(executor);
		return new InfractionCreateRequest();
	}

	public static InfractionCreateRequest from(String id, String[] regions, String reason, String proof, String executor) {
		return from(new String[]{id}, regions, reason, proof, executor);
	}
}
