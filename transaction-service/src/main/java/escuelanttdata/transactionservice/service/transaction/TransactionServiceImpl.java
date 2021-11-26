package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.client.ProductClient;
import escuelanttdata.transactionservice.client.model.Product;
import escuelanttdata.transactionservice.client.model.TypeProduct;
import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.model.transaction.TypeTransaction;
import escuelanttdata.transactionservice.repository.TransactionRepository;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    ProductClient productClient;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Completable save(Transaction transaction) {
        String nameProductType;
        Product product;
        product = productClient.getById(transaction.getProductId());
        nameProductType = product.getProductType().getName();

        Optional<String> optionaltransaction = Optional.of(transaction.getTypeTransaction());
        Optional<BigDecimal> optionalBalance = Optional.of(product.getBalance());
        Optional<String> optionaltype = Optional.of(nameProductType);

        optionaltype.filter(ota -> ota.equals(TypeProduct.savingAccount.toString())
                        || ota.equals(TypeProduct.currentAccount.toString())
                        || ota.equals(TypeProduct.FixedTermAccount.toString()))
                .ifPresent(a -> {

                    optionalBalance.filter(o -> o.compareTo(BigDecimal.ZERO) > 0)
                            .filter(p -> p.compareTo(transaction.getAmount()) >= 0)
                            .ifPresent(c -> {
                                optionaltransaction.filter(p -> p.equals(TypeTransaction.WITHDRAW.toString()))
                                        .ifPresent(b -> {
                                            product.setBalance(product.getBalance().subtract(transaction.getAmount()));
                                            productClient.updateProduct(product);
                                            transactionRepository.save(transaction);
                                        });
                            });

                    optionaltransaction.filter(p -> p.equals(TypeTransaction.DEPOSIT.toString()))
                            .ifPresent(b -> {
                                product.setBalance(product.getBalance().add(transaction.getAmount()));
                                productClient.updateProduct(product);
                                transactionRepository.save(transaction);
                            });
                });

        return Completable.fromCallable(() -> Optional.of(optionaltype).orElseThrow(()->new NotSavedException("Not Saved")));
    }

    @Override
    public Maybe<Optional<List<Transaction>>> getTransactionByProductId(Integer id) {
        return Maybe.fromCallable(()->Optional.of(transactionRepository.getTransactionByProductId(id).orElseThrow(()->new NotFountException("Not Fount"))));
    }

}
