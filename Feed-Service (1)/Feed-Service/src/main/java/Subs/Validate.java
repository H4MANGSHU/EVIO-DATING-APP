package Subs;

import DATABASES.SubscriptionRepository;
import Entites.Subscription;
import Enums.PLAN;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class Validate {

    private final SubscriptionRepository subscriptionRepository;

    @Retryable(
            retryFor = {
                    CannotGetJdbcConnectionException.class,
                    TransientDataAccessException.class
            },
            maxAttempts = 5,
            backoff = @Backoff(delay = 500))

    public boolean IsSubscribed(Subscription subscription){

        Boolean isDone = subscription.isSub() &&
                subscription.getEndPlan().isBefore(LocalDateTime.now());
        if (isDone){
            Expire(subscription);
            return false;

        }
        return subscription.isSub();
    }


    private void Expire(Subscription subscription){
        subscription.setSub(false);
        subscription.setPlanStatus(PLAN.EXPIRED);
        subscriptionRepository.save(subscription);

    }
    @Recover
    private String fall(Exception exception){
        throw new RuntimeException("fuck off!"+exception.getMessage());
    }
}
