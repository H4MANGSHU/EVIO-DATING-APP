package RestApi;

import DTOS.PaymentDTO;
import Payement_Service.RazorPay;
import SAVETODB.SAVE_DB;
import SAVETODB.SAVE_USER_TO_DB;
import com.razorpay.RazorpayException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CONTROLLERS {

    private RazorPay razorPay;
    private SAVE_DB saveUserToDb;

    @PostMapping("/Pay")
    public ResponseEntity<?> CreatePayment(@Valid @RequestBody PaymentDTO paymentDTO) throws RazorpayException {
        razorPay.CreatePayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/Webhook")
    public ResponseEntity<?> WebHook(@RequestBody String pay, @RequestHeader("X-Razorpay-Signature") String Sign) {
        razorPay.ProcessWebHooks(pay,Sign);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/save")
    public ResponseEntity<@NonNull String>SaveAuth(@AuthenticationPrincipal OidcUser oidcUser){
        saveUserToDb.SaveAuth(oidcUser);
        return ResponseEntity.ok().build();
    }


}
