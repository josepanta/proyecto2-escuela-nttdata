package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import io.reactivex.Completable;
import io.reactivex.Maybe;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Completable save(Transaction transaction);

    Maybe<Optional<List<Transaction>>> getTransactionByProductId(Integer id);

}
