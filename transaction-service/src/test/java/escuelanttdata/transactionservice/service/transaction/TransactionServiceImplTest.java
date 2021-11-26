package escuelanttdata.transactionservice.service.transaction;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.repository.TransactionRepository;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImplTest {
    @Mock
    TransactionRepository TransactionRepository;

    @InjectMocks
    TransactionServiceImpl transactionServiceimpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getTransactionByProductId_whenReturnsAListProducts() {
        Mockito.when(TransactionRepository.getTransactionByProductId(1)).thenReturn(buildListTransactions());
        transactionServiceimpl.getTransactionByProductId(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(transactionList -> transactionList.get().size() == 1);
    }

    @Test
    void test_getChargeByProductId_whenReturnsAListProductsEmpty() {
        Mockito.when(TransactionRepository.getTransactionByProductId(36)).thenReturn(Optional.of(Arrays.asList(new Transaction())));
        transactionServiceimpl.getTransactionByProductId(1)
                .test()
                .assertNotComplete()
                .assertError(NotFountException.class)
                .assertError(error -> ((NotFountException) error).getMessage().equals("Not Fount"));
    }

    private Optional<List<Transaction>> buildListTransactions() {
        return Optional.of(Arrays.asList(new Transaction(1, new BigDecimal(550), "DEPOSIT", new Date(), 1)));
    }
}
