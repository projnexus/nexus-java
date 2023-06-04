package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {
    private String id;
    private String userId;
    private Region[] regions;
    private String reason;
    private String proof;
    private Long executor;
    private Boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
