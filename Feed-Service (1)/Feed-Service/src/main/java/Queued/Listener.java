package Queued;


import DTOS.PaymentDTO;
import Entites.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class Listener {

    @KafkaListener(topics = "Payment_topic",concurrency = "3",groupId ="#payment-group" )
    private void KafkaListener(PaymentDTO paymentDTO){
        try {
            Payment payment = Payment.builder()
                    .paymentId(paymentDTO.paymentId())
                    .isPaid(true)
                    .status(paymentDTO.status())
                    .amount(paymentDTO.amount())
                    .userId(paymentDTO.userId())
                    .build();
        } catch (Exception e) {
            log.error("failed payment {}",e);
        }

    }
    @DltHandler
    private void DLT(){

    }
}
