package DATABASES;


import DTOS.FeedsDTO;
import DTOS.RequestDTO;
import Entites.Feeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feeds,Long> {

    @Query(value = "SELECT f.AuthorId ,f.Description,f.mediaUrl,f.postedAt,f.caption FROM feed_table f where f.AuthorId=:AuthorId",nativeQuery = true)
    Optional<FeedsDTO> findAuthorById(@Param("AuthorId") String AuthorId);
}
