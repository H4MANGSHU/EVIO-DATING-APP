package SAVETODB;


import org.springframework.modulith.NamedInterface;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;



@NamedInterface
public interface SAVE_USER_TO_DB {

    public void SaveAuth(OidcUser oidcUser);
   // public void SaveJwt(Jwt jwt);
}
