package ENTITES;

import ENUMS.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "payment_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PaymentId;
    private String UserName;
    @Lob
    private String UserId;
    private String PayLoad;
    private boolean IsPaid;
    private String OrderId;
    private String razorpayPaymentId;
    private long Amount;
    private Date CreatedAt;
    private Date FailedAt;
    @Enumerated(EnumType.STRING)
    private PaymentType Status;


}
