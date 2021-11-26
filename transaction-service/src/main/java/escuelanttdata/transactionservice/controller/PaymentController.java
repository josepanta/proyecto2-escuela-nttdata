package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.service.payment.PaymentService;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment")
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    @PostMapping("/payment")
    public Maybe<ResponseEntity<Object>> savePayment(@Valid @RequestBody Payment payment) {

        return paymentService.save(payment)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body((Object) "Saved Charge"))
                .toMaybe()
                .onErrorResumeNext(this::buildError);
    }

    @GetMapping("/payment/product/{id}")
    public Maybe<ResponseEntity<Object>> getPaymentByProductId(@Min(1) @PathVariable Integer id) {

        return paymentService.getPaymentByProductId(id)
                .map(paymentList -> ResponseEntity.status(HttpStatus.OK).body((Object) paymentList))
                .onErrorResumeNext(this::buildError);
    }

    private Maybe<ResponseEntity<Object>> buildError(Throwable error) {

        if (error.getClass() == NotFountException.class)
            return Maybe.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage()));
        else if (error.getClass() == NotSavedException.class)
            return Maybe.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error.getMessage()));
        else return Maybe.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error"));
    }

}
