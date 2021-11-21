package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TransactionDao extends CrudRepository<Transaction,Integer> {

    //@Query("select t from Transaction t where t.accountId=:accountId")
    List<Transaction> findTransactionByProductId(@PathVariable Integer productId);

}
