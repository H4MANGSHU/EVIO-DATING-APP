package ENTITES;
import ENUMS.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Getter
@Setter
@Builder
public class Users implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int UserId;
    @Column(name = "name",nullable = false)
    private String UserName;
    @Column(name = "email", unique = true,nullable = false)
    private String Email;
    @Column(name = "number", nullable = false)
    private int  PhoneNumber;
    @Column(name = "dob",nullable = false)
    private Date DateOfBirth;
    @Column(name = "create_time",nullable = false)
    private Instant Created_At;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority>Authority = new HashSet<>();
        Authority.add(new SimpleGrantedAuthority("ROLE_" +roles.name()));
        return Authority;
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}


