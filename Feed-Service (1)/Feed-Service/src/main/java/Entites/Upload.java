package Entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "upload_table")
@Builder
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String UploadId;
    private String AuthorId;
    private String MediaUrl;
    private Instant PostedAt;
    private String Description;

}
