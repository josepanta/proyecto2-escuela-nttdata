package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.client.ProductClient;
import escuelanttdata.transactionservice.client.model.Product;
import escuelanttdata.transactionservice.client.model.TypeProduct;
import escuelanttdata.transactionservice.model.charge.Charge;
import escuelanttdata.transactionservice.repository.ChargeRepository;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Autowired
    ProductClient productClient;
    @Autowired
    ChargeRepository chargeRepository;

    @Override
    public Completable save(Charge charge) {

        Product product;
        String nameProductType;
        product = productClient.getById(charge.getProductId());
        nameProductType = product.getProductType().getName();

        Optional<String> optionaltype = Optional.of(nameProductType);
        optionaltype.filter(ota -> ota.equals(TypeProduct.CreditCard.toString()))
                .ifPresent(a -> {
                    product.setBalance(product.getBalance().subtract(charge.getAmount()));
                    productClient.updateProduct(product);
                    chargeRepository.save(charge);
                });

        return Completable.fromCallable(() -> Optional.of(optionaltype).orElseThrow(()->new NotSavedException("Not Saved")));
    }

    @Override
    public Maybe<Optional<List<Charge>>> getChargeByProductId(Integer id) {
        return Maybe.fromCallable(() -> Optional.of(chargeRepository.getChargeByProductId(id).orElseThrow(() -> new NotFountException("Not Fount"))));
    }
}
