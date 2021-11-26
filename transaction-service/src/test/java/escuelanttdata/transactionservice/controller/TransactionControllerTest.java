package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.service.transaction.TransactionServiceImpl;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
        Mockito.when(transactionServiceImpl.getTransactionByProductId(1)).thenReturn(buildListTransactions());
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

    @Nested
    @DisplayName("Method: save")
    class save {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_save_whenServiceSavePayment() {
                Mockito.when(transactionServiceImpl.save(new Transaction())).thenReturn(buildTransaction());
                transactionController.saveTransaction(new Transaction())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(objectResponseEntity -> objectResponseEntity.getStatusCode() == HttpStatus.CREATED);
            }

        }

        @Nested
        @DisplayName("UnHappy Path")
        class unHappyPath {

            @Test
            void test_save_whenServiceReturnNotPayment(){
                Mockito.when(transactionServiceImpl.save(new Transaction()))
                        .thenReturn(Completable.error(new NotSavedException("Not Saved")));
                transactionController.saveTransaction(new Transaction())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
            }

        }

        private Completable buildTransaction() {

            return Completable.fromCallable(() -> new Payment(1, new BigDecimal(550), new Date(), new Date(), 1));
        }
    }





    private Maybe<Optional<List<Transaction>>> buildListTransactions() {
        return Maybe.just(Optional.of(Arrays.asList(new Transaction(1, new BigDecimal(550), "DEPOSIT", new Date(), 1))));
    }
}
