package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.service.transaction.TransactionServiceImpl;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import io.reactivex.Maybe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionControllerTest {

    @Mock
    TransactionServiceImpl transactionServiceImpl;

    @InjectMocks
    TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAll_whenServiceReturnsAListProducts() {
        Mockito.when(transactionServiceImpl.getTransactionByProductId(1)).thenReturn(buildListCharges());
        transactionController.getTransactionByAccountId(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(listResponseEntity -> Stream.of(listResponseEntity.getBody()).collect(Collectors.toList()).size() == 1
                        && listResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void test_getTransactionByProductId_whenServiceReturnsNotFountError() {
        Mockito.when(transactionServiceImpl.getTransactionByProductId(1)).thenReturn(Maybe.error(new NotFountException("Not Fount")));
        transactionController.getTransactionByAccountId(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND
                        && ((String) responseEntity.getBody()).equals("Not Fount"));
    }

    private Maybe<Optional<List<Transaction>>> buildListCharges() {
        return Maybe.just(Optional.of(Arrays.asList(new Transaction(1, new BigDecimal(550), "DEPOSIT", new Date(), 1))));
    }
}
