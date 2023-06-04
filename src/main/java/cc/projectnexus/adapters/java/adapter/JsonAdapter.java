package cc.projectnexus.adapters.java.adapter;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

public class JsonAdapter {
	private final Jsonb jsonb;

	public JsonAdapter(JsonbConfig config) {
		this.jsonb = JsonbBuilder.create(config);
	}

	public JsonAdapter() {
		this.jsonb = JsonbBuilder.create();
	}

	public <T> T fromJson(String json, Class<T> clazz) {
		return jsonb.fromJson(json, clazz);
	}

	public String toJson(Object object) {
		return jsonb.toJson(object);
	}
}