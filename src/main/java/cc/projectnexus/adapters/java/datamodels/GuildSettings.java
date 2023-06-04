package cc.projectnexus.adapters.java.datamodels;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class GuildSettings {
    private String id;
    private String guildId;
    private boolean autoBan;
    private boolean autoUnban;
    private String logsChannel;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
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