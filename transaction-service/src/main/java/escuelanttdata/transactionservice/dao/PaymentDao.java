package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDao extends CrudRepository<Payment,Integer> {
}
