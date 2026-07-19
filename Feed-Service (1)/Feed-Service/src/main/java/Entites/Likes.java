package Entites;


import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;

@Entity
@Table(name = "like")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Likes {
    private String LikedId;
    private String AuthorId;
    private int LikeCnt;
    private Instant LikesAt;
    @LastModifiedBy
    private  Instant LikedBy;

     @PrePersist
    public void OnLikes(){
        this.LikesAt = Instant.now();
    }

}
