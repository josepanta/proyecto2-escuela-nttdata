package com.nttdata.featureclientservice.client;

import com.nttdata.featureclientservice.client.model.product.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-transaction")
public interface TransactionClient {
    @GetMapping("/transaction/product/{id}")
    List<Transaction> getTransactionByProductId(@PathVariable Integer id);


}
