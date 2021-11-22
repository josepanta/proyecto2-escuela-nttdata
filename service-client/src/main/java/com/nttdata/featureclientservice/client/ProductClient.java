package com.nttdata.featureclientservice.client;

import com.nttdata.featureclientservice.client.model.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product")
    List<Product> getProductAll();

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable Integer id);


}
