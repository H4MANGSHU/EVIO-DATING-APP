package DTOS;

import ENUMS.PaymentType;


import java.util.Date;

public record PaymentDTO(
        Long paymentId,
        String userId,
        String userName,
        String orderId,
        String razorpayPaymentId,
        long amount,
        boolean isPaid,
        PaymentType status,
        Date createdAt,
        Date failedAt
) {}


