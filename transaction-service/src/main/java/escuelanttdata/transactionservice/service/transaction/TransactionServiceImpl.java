package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.client.model.Product;
import escuelanttdata.transactionservice.client.ProductClient;
import escuelanttdata.transactionservice.dao.TransactionDao;
import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.model.transaction.TypeTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements  TransactionService{

    @Autowired
    ProductClient productClient;

    @Autowired
    TransactionDao transactionDao;

    @Override
    public void save(Transaction transaction) {
        Product product;
        product=productClient.getById(transaction.getProductId());
        Optional<String> optionaltransaction = Optional.of(transaction.getTypeTransaction());
        optionaltransaction.filter(p->p.equals(TypeTransaction.DEPOSIT.toString()))
                .ifPresent(b->{
                    product.setBalance(product.getBalance().add(transaction.getAmount()));
                    productClient.updateProduct(product);
                    transactionDao.save(transaction);
                });
        optionaltransaction.filter(p->p.equals(TypeTransaction.WITHDRAW.toString()))
                .ifPresent(b->{
                    product.setBalance(product.getBalance().subtract(transaction.getAmount()));
                    productClient.updateProduct(product);
                    transactionDao.save(transaction);
                });
    }

    @Override
    public List<Transaction> getTransactionByProductId(Integer accoundId) {
        List<Transaction> transactionList = new ArrayList<>();
        transactionDao.findTransactionByProductId(accoundId).forEach(transaction -> transactionList.add(transaction));
        return transactionList;
    }

}
