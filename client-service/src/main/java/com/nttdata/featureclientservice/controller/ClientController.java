package com.nttdata.featureclientservice.controller;

import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import com.nttdata.featureclientservice.service.ClientServiceImpl;
import com.nttdata.featureclientservice.utils.exceptions.NotFoundException;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    @Qualifier("clientServiceImpl")
    ClientServiceImpl clientServiceImpl;

    @GetMapping("getClients")
    public Maybe<ResponseEntity<Object>> getClientAll() {

        return clientServiceImpl.getAll()
                .map(clients -> ResponseEntity.status(HttpStatus.OK).body((Object) clients))
                .onErrorResumeNext(this::buildError);
    }

    @GetMapping("/clientProductById/{idClient}")
    public List<Product> getClientClientById(@PathVariable Integer idClient) {
        return clientServiceImpl.getProductAll(idClient);
    }

    @GetMapping("balanceById/{idClient}")
    public BigDecimal getBalanceId(@PathVariable Integer idClient) {
        return clientServiceImpl.getBalanceById(idClient).getBalance();
    }

    @GetMapping("/transaction/{id}")
    public List<Transaction> getTransaccion(@PathVariable Integer id) {
        return clientServiceImpl.getTransactionByClientId(id);
    }

    private Maybe<ResponseEntity<Object>> buildError(Throwable error) {

        if (error.getClass() == NotFoundException.class)
            return Maybe.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage()));
        else if (error.getClass() == NotFoundException.class)
            return Maybe.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error.getMessage()));
        else return Maybe.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error"));
    }


}
