package Entites;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "feed_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feeds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FeedId;
    private String AuthorId;
    private String Caption;
    private String Description;
    private String MediaUrl;
    private Instant PostedAt;
    private  Instant UpdatedAt;
    private int CommentCnt;

    @PrePersist
    public void onPost(){
        PostedAt = Instant.now();
    }
    @PreUpdate
    public void OnUpdate(){
        UpdatedAt = Instant.now();
    }
}
