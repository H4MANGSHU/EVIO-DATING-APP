package DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;
import java.util.UUID;

@Data
public class ProfileDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ProfileId;
    private String Hobby;
    private String Bio;
    private String Gender;
    private String Preferences;
    private String Location;
    private Date DateOfBirth;
    private String Interests;
    private UUID TribeId = UUID.randomUUID();
    private String TribeName;
    private int Age;
    private String RelationShipStatus;
    @CreatedBy
    private  String CreatedBy;

}
