package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
    private Timestamp lastUpdated;

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.lastUpdated = updatedAt;
    }
}
