package escuelanttdata.transactionservice.repository;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<List<Transaction>> getTransactionByProductId(@PathVariable Integer productId);

}
