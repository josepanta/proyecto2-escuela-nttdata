package com.nttdata.featureclientservice.client;

import com.nttdata.featureclientservice.client.model.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductClient {

    @GetMapping("/product")
    List<Product> getProductAll();

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable Integer id);


}
