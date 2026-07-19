package DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedsDTO {
    private Long FeedId;
    private String AuthorId;
    private String Caption; //5
    private String Description;  //1
    private String MediaUrl;  //2
    private Instant PostedAt; //3
    private  Instant UpdatedAt; //4
    private int LikeCnt;
    private int CommentCnt;
    private double RankingScore;

}
