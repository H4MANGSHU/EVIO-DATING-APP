package Subs;

import DATABASES.PaymentRepo;
import DTOS.PaymentDTO;
import Entites.Payment;
import Entites.Subscription;
import Enums.PLAN;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class Subscr {

    private final PaymentRepo paymentRepo;

    public void Subscription(PaymentDTO paymentDTO,String SubId){

        Payment payment = paymentRepo.findPayment(paymentDTO.paymentId())
                .orElseThrow(()-> new RuntimeException("Take Subscription!"));
        if (!payment.isPaid()){
            throw new RuntimeException("Please Complete Payment & take subscription!");
        }
        Subscription subscription = Subscription.builder()
                .subId(SubId)
                .UserId(paymentDTO.userId())
                .isSub(paymentDTO.isPaid())
                .PlanStatus(PLAN.PREMIUM)
                .StartPlan(LocalDateTime.now())
                .EndPlan(LocalDateTime.now().plusMonths(1))
                .build();

    }

}
