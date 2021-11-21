package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.dao.TransactionDao;
import escuelanttdata.transactionservice.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements  TransactionService{
    @Autowired
    TransactionDao transactionDao;

    @Override
    public void save(Transaction transaction) {
        transactionDao.save(transaction);
    }
}
