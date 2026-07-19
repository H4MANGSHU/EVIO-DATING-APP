package SERVICES;

import DATABASES.FeedRepository;
import DTOS.FeedsDTO;
import DTOS.RequestDTO;
import Entites.Feeds;
import KEYMANAGER.Keys;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

@Service
@RequiredArgsConstructor
public class FetchingDb {

    private final FeedRepository feedRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final Keys keys;

    public FeedsDTO FetchDb(String AuthorId) {
        var Id =keys.UserKey(AuthorId);
       Optional <FeedsDTO> Fetch = feedRepository.findAuthorById(AuthorId);
        if (Fetch.isEmpty()) {

            return null;
        }
      return Fetch.orElse(null);
    }

    public FeedsDTO StoreInDB(FeedsDTO feedsDTO){
        Optional <FeedsDTO>Temp = feedRepository.findAuthorById(feedsDTO.getAuthorId());
        if (Temp.isPresent()){
            Feeds feeds = Feeds.builder()
                    .FeedId(feedsDTO.getFeedId())
                    .CommentCnt(feedsDTO.getCommentCnt())
                    .Description(feedsDTO.getDescription())
                    .UpdatedAt(Instant.now())
                    .MediaUrl(feedsDTO.getMediaUrl())
                    .Caption(feedsDTO.getCaption())
                    .AuthorId(feedsDTO.getAuthorId())
                    .PostedAt(Instant.now())
                    .build();

        }
        return feedsDTO;
    }


}
