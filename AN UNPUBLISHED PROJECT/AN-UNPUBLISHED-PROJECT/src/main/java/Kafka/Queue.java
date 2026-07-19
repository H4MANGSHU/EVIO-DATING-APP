package Kafka;


import DTOS.PaymentDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class Queue {
    private  KafkaTemplate<@NonNull String,@NonNull Object>kafkaTemplate;


     private void Sending(PaymentDTO paymentDTO){
        try {
            SendResult<@NonNull String, @NonNull Object> result = kafkaTemplate.send("Payment-topic", paymentDTO.razorpayPaymentId()).get();
            RecordMetadata recordMetadata = result.getRecordMetadata();
         log.info("{}",recordMetadata.partition());
         log.info("{}",recordMetadata.hasTimestamp());
         log.info("{}",recordMetadata.hasOffset());
         log.info("{}",recordMetadata.topic());
         log.info("{}",recordMetadata.timestamp());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
