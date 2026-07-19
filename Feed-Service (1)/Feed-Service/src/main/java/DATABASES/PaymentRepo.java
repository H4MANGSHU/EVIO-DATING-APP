package DATABASES;

import Entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {

    @Query(value = "SELECT p.status,p.amount p.razorpayPaymentId AS User_info from Payment_table where p.userId = :Id",nativeQuery = true)
    Optional<Payment> findPayment(@Param("Id") Long Id);

}
