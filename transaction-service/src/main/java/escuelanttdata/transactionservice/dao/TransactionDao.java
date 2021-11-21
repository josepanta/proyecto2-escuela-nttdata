package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDao extends CrudRepository<Transaction,Integer> {

}
