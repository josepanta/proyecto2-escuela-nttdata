package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.model.payment.Payment;
import io.reactivex.Completable;
import io.reactivex.Maybe;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

   List<Payment> getAll();

   Completable save(Payment payment);

   Maybe<Optional<List<Payment>>> getPaymentByProductId(Integer id);
}
