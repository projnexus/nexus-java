package cc.projectnexus.adapters.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NexusClientProperties {
    private boolean useTokenAuthorize;
    private String token;
    private boolean debug;
}
