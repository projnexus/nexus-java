package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id", id);
        json.put("guildId", guildId);
        json.put("auto_ban", autoBan);
        json.put("auto_unban", autoUnban);
        json.put("logs_channel", logsChannel);
        json.put("createdAt", createdAt.toString());
        json.put("lastUpdated", lastUpdated.toString());

        JSONArray regions = new JSONArray();
        for (Region region : enabledRegions) {
            regions.put(region.toString());
        }

        json.put("enabled_regions", regions);

        return json;
    }

    public static GuildSettings fromJson(JSONObject json) {
        GuildSettings guildSettings = new GuildSettings();

        guildSettings.id = json.optLong("id");
        guildSettings.guildId = json.optString("guildId");
        guildSettings.autoBan = json.optBoolean("auto_ban");
        guildSettings.autoUnban = json.optBoolean("auto_unban");
        guildSettings.logsChannel = json.optString("logs_channel");
        guildSettings.createdAt = Timestamp.valueOf(json.optString("createdAt"));
        guildSettings.lastUpdated = Timestamp.valueOf(json.optString("updatedAt"));

        JSONArray regionsArray = json.optJSONArray("enabled_regions");
        if (regionsArray != null) {
            Region[] regions = new Region[regionsArray.length()];
            for (int i = 0; i < regionsArray.length(); i++) {
                regions[i] = Region.valueOf(regionsArray.optString(i));
            }
            guildSettings.enabledRegions = regions;
        }

        return guildSettings;
    }
}