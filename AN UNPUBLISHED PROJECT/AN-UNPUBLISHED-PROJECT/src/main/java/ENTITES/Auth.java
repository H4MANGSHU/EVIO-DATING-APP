package ENTITES;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "Auth_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String AuthId;
    @Column(name = "username",unique = true,nullable = false)
    private String UserName;
    @Column(name = "email",unique = true,nullable = false)
    private String Email;
    @Column(name = "LoggedTme",nullable = false)
    private Instant LoggedAt;
}
