package SAVETODB;
import DATABASES.AuthRepository;
import ENTITES.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SAVE_DB implements SAVE_USER_TO_DB {

    private final AuthRepository authRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void SaveAuth(OidcUser oidcUser) {

        var KeyCloakId = oidcUser.getSubject();
        var usrName = oidcUser.getName();
        var email = oidcUser.getEmail();
        Instant LoggedInAt = oidcUser.getAuthenticatedAt();// instant
        Auth FindHoes= authRepository.FindByName(KeyCloakId)
                .orElse(new Auth());
        Auth SaveInfo = Auth.builder()
                .AuthId(KeyCloakId)
                .UserName(usrName)
                .LoggedAt(LoggedInAt)
                .build();
         authRepository.save(SaveInfo);
        //FIND FIRST IF NOBODY IS THERE THEN JUST CREATE NEW USER AND SAVE IT TO DB
        //SAVE TO DB JUST
        applicationEventPublisher.publishEvent(email);
    }

}
