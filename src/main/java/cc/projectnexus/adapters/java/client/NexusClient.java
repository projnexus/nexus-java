package cc.projectnexus.adapters.java.client;

import cc.projectnexus.adapters.java.component.AuthorizeComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NexusClient {
	protected static NexusClient instance;
	protected NexusClientProperties properties;

	/**
	 * Create a new constructor of NexusClient.
	 * @param properties The properties.
	 */
	public NexusClient(NexusClientProperties properties) {
		instance = this;
		this.properties = properties;
		run();
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

	public abstract void run();
}