package cc.projectnexus.adapters.java.datamodels;

import lombok.*;

import javax.json.bind.annotation.JsonbProperty;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class GuildSettings {
    @JsonbProperty("id")
    private String id;

    @JsonbProperty("guildId")
    private String guildId;

    @JsonbProperty("auto_ban")
    private boolean autoBan;

    @JsonbProperty("auto_unban")
    private boolean autoUnban;

    @JsonbProperty("logs_channel")
    private String logsChannel;

    @JsonbProperty("createdAt")
    private Timestamp createdAt;

    @JsonbProperty("updatedAt")
    private Timestamp lastUpdated;

    @JsonbProperty("enabled_regions")
    private Region[] enabledRegions;

    public GuildSettings() {

    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.lastUpdated = updatedAt;
    }
}