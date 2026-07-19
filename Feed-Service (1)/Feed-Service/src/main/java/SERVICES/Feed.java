package SERVICES;
import DATABASES.FeedRepository;
import DATABASES.SwapRepository;
import DTOS.FeedsDTO;
import DTOS.LikesDTO;
import DTOS.SwapDTO;
import Entites.Likes;
import Entites.SWAP;
import Entites.Subscription;
import KEYMANAGER.Keys;
import Subs.Validate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.List;



@Service
@RequiredArgsConstructor
public class Feed {

    private final StringRedisTemplate stringRedisTemplate;
    private final Keys keys;
    private final ObjectMapper objectMapper;
    private final FeedRepository feedRepository;
    private final FetchingDb fetchingDb;
    private final Validate validate;
    private static final long UNPAID_LIKE_LIMIT = 30;
    private final SwapRepository swapRepository;

    public List<FeedsDTO> CheckFeed(String AuthorId) throws JsonProcessingException, Exception {
        var feeds = getFromCached(AuthorId);
        if (feeds != null) {
            return List.of(feeds);
        }
        FeedsDTO FetchFromDb = fetchingDb.FetchDb(AuthorId);
        if (FetchFromDb != null) {
            return CachedPost(AuthorId, FetchFromDb);

        }

        return List.of();
    }

    private List<FeedsDTO> CachedPost(String AuthorId, FeedsDTO feeds) throws JsonProcessingException, Exception {

        String AuthId = keys.UserKey(AuthorId);
        String stored = objectMapper.writeValueAsString(feeds);
        if (!stored.isEmpty() && AuthId != null) {
            stringRedisTemplate.opsForValue().set(AuthId, stored, Duration.ofHours(4));
            return List.of();
        }
        return null;
    }


    private FeedsDTO getFromCached(String authorId) throws Exception {

        String authId = keys.UserKey(authorId);

        String getAll = stringRedisTemplate.opsForValue().get(authId);

        if (getAll == null || getAll.isBlank()) {
            return null;
        }
        FeedsDTO feedsDTO = objectMapper.readValue(getAll, FeedsDTO.class);
        return feedsDTO;

    }

    public int Like(LikesDTO likesDTO, Subscription subscription) {
        var key = "user:" + likesDTO.getAuthorId();
        if (!validate.IsSubscribed(subscription)) {
            var StoredLikes = stringRedisTemplate.opsForValue().increment(key);
            if (StoredLikes >= UNPAID_LIKE_LIMIT) {
                throw new RuntimeException("Take Subscription {}" + subscription.getUserId());
            }
            Likes likes = Likes.builder()
                    .LikedId(likesDTO.getLikedId())
                    .LikeCnt(likesDTO.getLikeCnt())
                    .AuthorId(likesDTO.getAuthorId())
                    .LikesAt(Instant.now())
                    .build();
            return Math.toIntExact(StoredLikes);

        }
        return likesDTO.getLikeCnt();
    }

        public void Swap(SwapDTO swap){
         if(swap.getSwap()< 0 || swap.getSwap()>1) {
           return;
         }
            SWAP swap1 = SWAP.builder()
                    .SwapId(swap.getSwapId())
                    .UserId(swap.getUserId())
                    .Swap(swap.getSwap())
                    .build();
         swapRepository.save(swap1);

         }
         }


   
