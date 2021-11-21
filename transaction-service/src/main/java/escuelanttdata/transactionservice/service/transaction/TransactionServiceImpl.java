package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.dao.TransactionDao;
import escuelanttdata.transactionservice.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements  TransactionService{
    @Autowired
    TransactionDao transactionDao;

    @Override
    public void save(Transaction transaction) {
        transactionDao.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByProductId(Integer accoundId) {
        List<Transaction> transactionList = new ArrayList<>();
        transactionDao.findTransactionByProductId(accoundId).forEach(transaction -> transactionList.add(transaction));
        return transactionList;
    }

}
