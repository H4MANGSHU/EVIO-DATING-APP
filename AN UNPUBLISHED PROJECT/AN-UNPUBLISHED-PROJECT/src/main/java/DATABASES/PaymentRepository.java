package DATABASES;

import ENTITES.Payments;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NullMarked
public interface PaymentRepository extends JpaRepository<Payments,Long> {
    Optional< Payments> Find_ByOrderId (String OrderId);
    Optional< Payments> FindByPaYmEnTId (String razorpayPaymentId);
}
