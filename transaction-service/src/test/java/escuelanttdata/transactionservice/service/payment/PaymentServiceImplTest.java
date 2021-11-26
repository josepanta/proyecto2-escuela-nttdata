package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.repository.PaymentRepository;
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

public class PaymentServiceImplTest {
    @Mock
    PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceImpl paymentServiceimpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getPaymentByProductId_whenReturnsAListProductsById() {
        Mockito.when(paymentRepository.getPaymentByProductId(1)).thenReturn(buildListPayment());
        paymentServiceimpl.getPaymentByProductId(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(chargeList -> chargeList.get().size() == 1);
    }

    @Test
    void test_getPaymentByProductId_whenReturnsAListProductsEmpty() {
        Mockito.when(paymentRepository.getPaymentByProductId(36)).thenReturn(Optional.of(Arrays.asList(new Payment())));
        paymentServiceimpl.getPaymentByProductId(1)
                .test()
                .assertNotComplete()
                .assertError(NotFountException.class)
                .assertError(error -> ((NotFountException) error).getMessage().equals("Not Fount"));
    }

    private Optional<List<Payment>> buildListPayment() {
        return Optional.of(Arrays.asList(new Payment(1, new BigDecimal(550), new Date(), new Date(), 1)));
    }
}
