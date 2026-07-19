package ENTITY;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ProfileId;
    private String Hobby;
    private String Bio;
    private String Gender;
    private String Preferences;
    private String Location;
    private String Interests;
    private Date DateOfBirth;
    private UUID TribeId = UUID.randomUUID();
    private String TribeName;
    private int Age;
    private String RelationShipStatus;
    @CreatedBy
    private  String CreatedBy;

}
