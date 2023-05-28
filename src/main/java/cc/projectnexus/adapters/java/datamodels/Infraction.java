package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {
    private Long id;
    private Long userId;
    private List<String> userIdentifiers;
    private Region[] regions;
    private String infractionReason;
    private String infractionProof;
    private Timestamp infractionTime;
    private Long executorId;
}
