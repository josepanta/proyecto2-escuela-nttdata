package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.model.charge.Charge;
import escuelanttdata.transactionservice.repository.ChargeRepository;
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

public class ChargeServiceImplTest {
    @Mock
    ChargeRepository chargeRepository;

    @InjectMocks
    ChargeServiceImpl chargeServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getChargeByProductId_whenReturnsAListProducts() {
        Mockito.when(chargeRepository.getChargeByProductId(1)).thenReturn(buildListCharges());
        chargeServiceImpl.getChargeByProductId(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(chargeList -> chargeList.get().size() == 1);
    }

    @Test
    void test_getChargeByProductId_whenReturnsAListProductsEmpty() {
        Mockito.when(chargeRepository.getChargeByProductId(36)).thenReturn(Optional.of(Arrays.asList(new Charge())));
        chargeServiceImpl.getChargeByProductId(1)
                .test()
                .assertNotComplete()
                .assertError(NotFountException.class)
                .assertError(error -> error.getMessage().equals("Not Fount"));
    }


    private Optional<List<Charge>> buildListCharges() {
        return Optional.of(Arrays.asList(new Charge(1, new BigDecimal(550), new Date(), new Date(), "Pago de Internet", 1)));
    }

}
