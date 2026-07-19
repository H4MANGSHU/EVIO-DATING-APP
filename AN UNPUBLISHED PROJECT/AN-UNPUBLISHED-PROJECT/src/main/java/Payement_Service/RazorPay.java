package Payement_Service;


import DATABASES.PaymentRepository;
import DTOS.PaymentDTO;
import ENTITES.Payments;
import ENUMS.PaymentType;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
@Slf4j
@Service
@RequiredArgsConstructor
public class RazorPay {

    @Value("${razorpay.key-id}")
    private String keyId;
    @Value("${razorpay.key-secret}")
    private String razorpaySec;
    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepository;

    public void HandledAll(String Payload, String Signature) throws RazorpayException {
        ProcessWebHooks(Payload, Signature);
        JSONObject jsonObject = new JSONObject(Payload);
        var Event = jsonObject.getString("event");
        switch (Event) {
            case "payment.captured" -> markAsPaid(jsonObject);
            case "payment.failed" -> markAsFailed(jsonObject);
            default -> throw new IllegalStateException("Unexpected EVENTS " + Event);
        }
    }

    public void ProcessWebHooks(String Payloads, String Signature) {
        try {
            Utils.verifyWebhookSignature(Payloads, Signature, razorpaySec);

        } catch (RazorpayException e) {
            log.error("INVALID WEBHOOKS{}", e.getMessage());
        }
    }

    public void CreatePayment(PaymentDTO paymentDTO) throws RazorpayException {
        JSONObject Opt = new JSONObject();
        Opt.put("amount", paymentDTO.amount());
        Opt.put("currency", "INR");
        Opt.put("receipt", "user_" + paymentDTO.userId() + "_" + System.currentTimeMillis());
        Order CreateOrder = razorpayClient.orders.create(Opt);
        Payments payments = paymentRepository.Find_ByOrderId(paymentDTO.razorpayPaymentId())
                .orElse(new Payments());
            Payments payments1 = Payments
                    .builder()
                    .CreatedAt(Date.from(Instant.now()))
                    .Amount(payments.getAmount())
                    .IsPaid(true)
                    .UserId(paymentDTO.userId())
                    .Status(PaymentType.SUCCESS)
                    .razorpayPaymentId(paymentDTO.razorpayPaymentId())
                    .UserName(paymentDTO.userName())
                    .build();
              paymentRepository.save(payments1);
        // do order new order AND SAVE IT TO DB


    }

    @Transactional
    public void markAsPaid(JSONObject events) {

        String event = events.getString("event");

        if (!event.equals("payment.captured")) {
            return;
        }

        JSONObject paymentJson = events
                .getJSONObject("payload")
                .getJSONObject("payment")
                .getJSONObject("entity");

        String orderId = paymentJson.getString("order_id");
        String razorpayPaymentId = paymentJson.getString("id");
        String razorpayStatus = paymentJson.getString("status");

        if (!razorpayStatus.equals("payment.failed")) {
            return;
        }

        Payments payment = paymentRepository
                .Find_ByOrderId(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Payment not found!")
                );

        if (payment.getStatus() == PaymentType.SUCCESS) {
            return;
        }

        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setIsPaid(true);
        payment.setStatus(PaymentType.SUCCESS);
        payment.setCreatedAt(Date.from(Instant.now()));

        paymentRepository.save(payment);

    }

    @Transactional
    public void markAsFailed(JSONObject events) {

        String event = events.getString("event");

        if (!event.equals("payment.failed")) {
            return;
        }

        JSONObject paymentJson = events
                .getJSONObject("payload")
                .getJSONObject("payment")
                .getJSONObject("entity");

        String orderId = paymentJson.getString("order_id");
        String razorpayPaymentId = paymentJson.getString("id");
        String razorpayStatus = paymentJson.getString("status");

        if (!razorpayStatus.equals("failed")) {
            return;
        }

        Payments payment = paymentRepository
                .Find_ByOrderId(orderId)
                .orElseThrow(() ->
                        new NotFoundException("Payment not found")
                );

        if (payment.getStatus() == PaymentType.FAILED) {
            return;
        }

        payment.setRazorpayPaymentId(razorpayPaymentId);
        payment.setIsPaid(false);
        payment.setStatus(PaymentType.FAILED);
        payment.setCreatedAt(Date.from(Instant.now()));

        paymentRepository.save(payment);

    }

    public void Refund(String PaymentId, Double amount) {
        try {
            Payments payments = paymentRepository.Find_ByOrderId(PaymentId)
                    .orElseThrow(() -> new NotFoundException("NOT FOUND!"));
            if (amount.isInfinite()) {
                return;
            }
            razorpayClient.payments.refund(PaymentId);
        } catch (RazorpayException e) {
            log.error("Refund Failed {}", e.getMessage());
        }
    }

    public PaymentDTO Invoices(PaymentDTO paymentDTO) {
        Payments payment = paymentRepository.FindByPaYmEnTId(paymentDTO.razorpayPaymentId())
                .orElseThrow(() -> new NotFoundException("DO THE PAYMENT FIRST NIGGA"));

        return new PaymentDTO(payment.getPaymentId(),
                payment.getUserName(),
                payment.getPayLoad(),
                payment.getOrderId(),
                payment.getRazorpayPaymentId(),
                payment.getAmount(),
                payment.isIsPaid(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getFailedAt()
        );
    }
}
