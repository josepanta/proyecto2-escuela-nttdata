package escuelanttdata.transactionservice.repository;

import escuelanttdata.transactionservice.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    Optional<List<Payment>> getPaymentByProductId(@PathVariable Integer productId);
}
