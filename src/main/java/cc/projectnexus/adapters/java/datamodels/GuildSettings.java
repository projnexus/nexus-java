package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuildSettings {
    private Long id;
    private String guildId;
    private boolean autoBan;
    private boolean autoUnban;
    private String logsChannel;
    private Timestamp createdAt;
    private Timestamp lastUpdated;
    private Region[] enabledRegions;
}