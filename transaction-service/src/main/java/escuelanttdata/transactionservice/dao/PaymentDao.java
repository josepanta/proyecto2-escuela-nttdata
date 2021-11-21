package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.payment.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDao extends CrudRepository<Payment,Integer> {
}
