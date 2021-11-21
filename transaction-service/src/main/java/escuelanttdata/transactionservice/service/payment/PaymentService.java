package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.model.payment.Payment;

import java.util.List;

public interface PaymentService {

   void save(Payment payment);

   List<Payment> getPaymentByAccountId(Integer id);
}
