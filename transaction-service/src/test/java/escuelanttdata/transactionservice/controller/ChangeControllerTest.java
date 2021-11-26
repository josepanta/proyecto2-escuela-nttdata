package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.charge.Charge;
import escuelanttdata.transactionservice.service.charge.ChargeServiceImpl;
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

public class ChangeControllerTest {

    @Mock
    ChargeServiceImpl chargeServiceimpl;

    @InjectMocks
    ChargeController chargeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Nested
    @DisplayName("Method: getChargeByProductId")
    class getChargeByProductId {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_getChargeByProductId_whenServiceReturnsAListProducts() {
                Mockito.when(chargeServiceimpl.getChargeByProductId(1)).thenReturn(buildListCharges());
                chargeController.getTransactionByProductId(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(listResponseEntity -> Stream.of(listResponseEntity.getBody()).collect(Collectors.toList()).size() == 1 && listResponseEntity.getStatusCode() == HttpStatus.OK);
            }
        }

        @Nested
        @DisplayName("UnHappy Path")
        class unHappyPath {

            @Test
            void test_getChargeByProductId_whenServiceReturnsNotFountError() {
                Mockito.when(chargeServiceimpl.getChargeByProductId(1)).thenReturn(Maybe.error(new NotFountException("Not Fount")));
                chargeController.getTransactionByProductId(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND
                                && ((String) responseEntity.getBody()).equals("Not Fount"));
            }

        }

        private Maybe<Optional<List<Charge>>> buildListCharges() {
            return Maybe.just(Optional.of(Arrays.asList(new Charge(1, new BigDecimal(550), new Date(), new Date(), "Payment to Energy Electric", 1))));
        }
    }

    @Nested
    @DisplayName("Method: save")
    class save {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_save_whenServiceSaveCharge() {

                Mockito.when(chargeServiceimpl.save(new Charge())).thenReturn(buildCharge());
                chargeController.saveCharge(new Charge())
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
            void test_save_whenServiceReturnNotSaved() {

                Mockito.when(chargeServiceimpl.save(new Charge()))
                        .thenReturn(Completable.error(new NotSavedException("Not Saved")));
                chargeController.saveCharge(new Charge())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
            }

        }


        private Completable buildCharge() {

            return Completable.fromCallable(() -> new Charge(1, new BigDecimal(550), new Date(), new Date(), "Payment to Energy Electric", 1));
        }

    }


}
