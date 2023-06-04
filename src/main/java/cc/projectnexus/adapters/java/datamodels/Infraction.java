package cc.projectnexus.adapters.java.datamodels;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {
    @JsonbProperty("id")
    private String id;

    @JsonbProperty("userId")
    private String userId;

    @JsonbProperty("regions")
    private Region[] regions;

    @JsonbProperty("reason")
    private String infractionReason;

    @JsonbProperty("proof")
    private String infractionProof;

    @JsonbProperty("executor")
    private Long executorId;

    @JsonbProperty("active")
    private Boolean active;

    @JsonbProperty("createdAt")
    private Timestamp createdAt;

    @JsonbProperty("updatedAt")
    private Timestamp updatedAt;

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
