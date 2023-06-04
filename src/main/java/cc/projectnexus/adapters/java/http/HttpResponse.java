package cc.projectnexus.adapters.java.http;

import java.util.List;

public enum HttpResponse {
	OK(200, "OK"),
	CREATED(201, "Created"),
	NO_CONTENT(204, "No Content"),
	BAD_REQUEST(400, "Bad Request"),
	UNAUTHORIZED(401, "Unauthorized"),
	FORBIDDEN(403, "Forbidden"),
	NOT_FOUND(404, "Not Found"),
	INTERNAL_SERVER_ERROR(500, "Internal Server Error");

	private final int statusCode;
	private final String displayName;

	HttpResponse(int statusCode, String displayName) {
		this.statusCode = statusCode;
		this.displayName = displayName;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String displayName() {
		return displayName;
	}

	public static List<HttpResponse> getAll() {
		return List.of(values());
	}

	public static HttpResponse getByStatusCode(int statusCode) {
		return getAll().stream().filter(f -> f.getStatusCode() == statusCode).findAny().get();
	}
}