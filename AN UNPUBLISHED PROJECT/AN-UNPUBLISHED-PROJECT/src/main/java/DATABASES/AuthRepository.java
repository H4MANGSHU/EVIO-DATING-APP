package DATABASES;

import ENTITES.Auth;
import org.hibernate.boot.jaxb.mapping.spi.JaxbPersistentAttribute;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth,String> {

    @Query(value = "SELECT A.AuthId AS Auth_Id from AuthId A where A.AuthId= :AuthId ",nativeQuery = true)
    Optional<Auth> FindByName(@Param("AuthId") String AuthId);
    }

