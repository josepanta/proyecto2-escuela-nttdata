package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.model.transaction.Transaction;

import java.util.List;

public interface PaymentService {

   List<Payment> getAll();

   void save(Payment payment);

   List<Payment> getPaymentByProductId(Integer id);
}
