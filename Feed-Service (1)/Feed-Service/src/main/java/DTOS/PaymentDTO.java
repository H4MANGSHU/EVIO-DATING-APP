package DTOS;

import Enums.PaymentType;

import java.util.Date;

public record PaymentDTO(
        Long paymentId,
        String userId,
        String razorpayPaymentId,
        long amount,
        boolean isPaid,
        PaymentType status,
        Date createdAt
) {}
