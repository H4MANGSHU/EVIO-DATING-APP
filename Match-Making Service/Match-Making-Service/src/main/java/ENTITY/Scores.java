package ENTITY;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
