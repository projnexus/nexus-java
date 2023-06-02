package cc.projectnexus.adapters.java;

import cc.projectnexus.adapters.java.component.AuthorizeComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NexusClient {
	protected static NexusClient instance;
	protected boolean authorize;
	protected NexusClientProperties properties;

	/**
	 * Create a new constructor of NexusClient.
	 * @param authorize If you want to authorize or not.
	 * @param properties The properties.
	 */
	public NexusClient(boolean authorize, NexusClientProperties properties) {
		instance = this;
		this.authorize = authorize;
		this.properties = properties;
		authorize();
	}

	/**
	 * Automatically enables authorize and provides the properties object.
	 * @param properties The properties object.
	 */
	public NexusClient(NexusClientProperties properties) {
		instance = this;
		this.authorize = true;
		this.properties = properties;
		authorize();
	}

	/**
	 * Create a new constructor of NexusClient.
	 */
	public NexusClient() {
		instance = this;
		this.authorize = false;
		this.properties = new NexusClientProperties("", false);
	}

	/**
	 * Handle the authorization to the API.
	 * @return Returning if the authorization worked our not.
	 */
	protected boolean authorize() {
		boolean result = false;
		if (authorize) {
			result = AuthorizeComponent.authorizeToken(properties.getToken());
			if (result) {
				System.out.println("Authorized Nexus Wrapper.");
			} else {
				System.out.println("Could not authorize to Nexus Wrapper.");
			}
		}
		return result;
	}

	/**
	 * Get the instance of the Nexus Client object.
	 * @return Returning the Client instance.
	 */
	public static NexusClient getInstance() {
		if (instance == null) {
			try {
				throw new IllegalAccessException("Could not access client as the client isn't set.");
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	public abstract void authSuccess();
	public abstract void authFailed();
}