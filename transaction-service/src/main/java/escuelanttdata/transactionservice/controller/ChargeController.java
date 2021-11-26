package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.charge.Charge;
import escuelanttdata.transactionservice.service.charge.ChargeService;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController

public class ChargeController {

    @Autowired
    ChargeService chargeService;

    @PostMapping("/charge")
    public Maybe<ResponseEntity<Object>> saveCharge(@Valid @RequestBody Charge charge) {
        return chargeService.save(charge)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body((Object) "Saved Charge"))
                .toMaybe()
                .onErrorResumeNext(this::buildError);
    }


    @GetMapping("/charge/product/{id}")
    public Maybe<ResponseEntity<Object>> getTransactionByProductId(@Min(1) @PathVariable Integer id) {
        return chargeService.getChargeByProductId(id)
                .map(chargeList -> ResponseEntity.status(HttpStatus.OK).body((Object) chargeList))
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