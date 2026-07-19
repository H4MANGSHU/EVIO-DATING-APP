package DTOS;

import jakarta.persistence.PrePersist;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;

@Data
public class LikesDTO {
    private String LikedId;
    private String AuthorId;
    private int LikeCnt;
    private Instant LikesAt;
    @LastModifiedBy
    private Instant LikedBy;

    @PrePersist
    public void OnLikes() {
        this.LikesAt = Instant.now();
    }
}
