package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.payment.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PaymentDao extends CrudRepository<Payment,Integer> {

    List<Payment> getPaymentByAccountId(@PathVariable Integer accountId);
}
