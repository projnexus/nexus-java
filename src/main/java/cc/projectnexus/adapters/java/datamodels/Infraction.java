package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {
    private Long id;
    private Long userId;
    private Region[] regions;
    private String infractionReason;
    private String infractionProof;
    private Long executorId;
    private Boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id", id);
        json.put("infractionUserId", userId);

        JSONArray regionsArray = new JSONArray();
        for (Region region : regions) {
            regionsArray.put(region.toString());
        }

        json.put("regions", regionsArray);

        json.put("reason", infractionReason);
        json.put("proof", infractionProof);
        json.put("executor", executorId);
        json.put("active", active);
        json.put("createdAt", createdAt.toString());
        json.put("updatedAt", updatedAt.toString());

        return json;
    }

    public static Infraction fromJson(JSONObject json) {
        Infraction infraction = new Infraction();

        infraction.id = json.optLong("id");
        infraction.userId = json.optLong("infractionUserId");

        JSONArray regionsArray = json.optJSONArray("regions");
        if (regionsArray != null) {
            Region[] regions = new Region[regionsArray.length()];
            for (int i = 0; i < regionsArray.length(); i++) {
                regions[i] = Region.valueOf(regionsArray.optString(i));
            }
            infraction.regions = regions;
        }

        infraction.infractionReason = json.optString("reason");
        infraction.infractionProof = json.optString("proof");
        infraction.executorId = json.optLong("executor");
        infraction.active = json.optBoolean("active");
        infraction.createdAt = Timestamp.valueOf(json.optString("createdAt"));
        infraction.updatedAt = Timestamp.valueOf(json.optString("updatedAt"));

        return infraction;
    }
}
