package cc.projectnexus.adapters.java.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NexusClientProperties {
    private String token;
    private boolean debug;
}