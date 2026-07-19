package JPA;


import ENTITY.Profile;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@NullMarked
public interface ProfileRepository extends JpaRepository<Profile, String> {


}
