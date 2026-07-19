package DTO;

import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class PreferencesDTO {
    private String ScoreId;
    private String Hobby;
    private String Bio;
    private String Gender;
    private String Preferences;
    private Double Distance;
    private Instant LastActiveAt;
    private String Interests;
    private UUID TribeId = UUID.randomUUID();
    private String TribeName;
    private int Age;
    private String RelationShipStatus;
    private int ReportCnt;
    private int LikesCnt;
    private int PhotoCnt;


    @PrePersist
    private void OnDate( ){
        this.LastActiveAt = Instant.now();


    }

}
