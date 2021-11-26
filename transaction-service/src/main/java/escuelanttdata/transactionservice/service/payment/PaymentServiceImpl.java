package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.client.ProductClient;
import escuelanttdata.transactionservice.client.model.Product;
import escuelanttdata.transactionservice.client.model.TypeProduct;
import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.repository.PaymentRepository;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    ProductClient productClient;
    @Autowired
    PaymentRepository paymentRepository;


    @Override
    public List<Payment> getAll() {
        List<Payment> paymentList = new ArrayList<>();
        paymentRepository.findAll().forEach(payment -> paymentList.add(payment));
        return paymentList;
    }

    @Override
    public Completable save(Payment payment) {
        Product product;
        String nameProductType;
        product = productClient.getById(payment.getProductId());
        nameProductType = product.getProductType().getName();

        Optional<String> optionaltype = Optional.of(nameProductType);
        optionaltype.filter(ota -> ota.equals(TypeProduct.PersonalCredit.toString()) || ota.equals(TypeProduct.BusinessCredit.toString()) || ota.equals(TypeProduct.CreditCard.toString()))
                .ifPresent(a -> {

                    product.setBalance(product.getBalance().add(payment.getAmount()));
                    productClient.updateProduct(product);
                    paymentRepository.save(payment);
                });
        return Completable.fromCallable(() -> Optional.of(optionaltype).orElseThrow(()->new NotSavedException("Not Saved")));
    }

    @Override
    public Maybe<Optional<List<Payment>>> getPaymentByProductId(Integer id) {
        return Maybe.fromCallable(() -> Optional.of(paymentRepository.getPaymentByProductId(id).orElseThrow(() -> new NotFountException("Not Fount"))));
    }
}
