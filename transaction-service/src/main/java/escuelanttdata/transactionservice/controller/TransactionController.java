package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.service.transaction.TransactionService;
import escuelanttdata.transactionservice.utils.exceptions.NotFountException;
import escuelanttdata.transactionservice.utils.exceptions.NotSavedException;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public Maybe<ResponseEntity<Object>> saveTransaction(@Valid @RequestBody Transaction transaction) {

        return transactionService.save(transaction)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body((Object) "Saved Charge"))
                .toMaybe()
                .onErrorResumeNext(this::buildError);
    }


    @GetMapping("/transaction/product/{id}")
    public Maybe<ResponseEntity<Object>> getTransactionByAccountId(@Min(1) @PathVariable Integer id) {

        return transactionService.getTransactionByProductId(id)
                .map(transactionList -> ResponseEntity.status(HttpStatus.OK).body((Object) transactionList))
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
