package com.nttdata.featureclientservice.controller;

import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import com.nttdata.featureclientservice.model.Client;
import com.nttdata.featureclientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    @Qualifier("clientServiceImpl")
    ClientService clientServiceImpl;

    @GetMapping("getClient")
    public List<Client> getClientAll(){
        return clientServiceImpl.getClientAll();
    }

    @GetMapping("ClientProductById/{id}")
    public List<Product> getClientProductById(@PathVariable Integer idClient)
    {
        return clientServiceImpl.getProductAll(idClient);
    }
    @GetMapping("balanceById/{id}")
    public BigDecimal getBalanceId(@PathVariable Integer idClient)
    {
        return clientServiceImpl.getBalanceById(idClient).getBalance();
    }
    @GetMapping("/transaction/{id}")
    public List<Transaction> getTransaccion(@PathVariable Integer id){
    return clientServiceImpl.getTransactionByProductId(id);
    }






}
