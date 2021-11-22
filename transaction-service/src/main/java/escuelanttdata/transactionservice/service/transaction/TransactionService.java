package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.model.transaction.Transaction;

import java.util.List;

public interface TransactionService {

    void save(Transaction transaction);

    List<Transaction> getTransactionByProductId(Integer id);

}
