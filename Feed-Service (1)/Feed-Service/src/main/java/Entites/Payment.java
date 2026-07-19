package Entites;

import Enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_table")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long paymentId;
   private  String userId;
   private String razorpayPaymentId;
   private long amount;
   private boolean isPaid;
   private PaymentType status;

}
