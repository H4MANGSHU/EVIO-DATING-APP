package ENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Preferences {
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

}
