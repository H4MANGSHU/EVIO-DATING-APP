package KEYMANAGER;

import Entites.Feeds;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class Keys {

    public String UserKey(String key){
        return "feed:"+key;
    }

    }
