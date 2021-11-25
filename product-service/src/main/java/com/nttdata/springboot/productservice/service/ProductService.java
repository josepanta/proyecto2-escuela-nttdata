package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Product;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface ProductService {
    Maybe<List<Product>> getAll();

    Single<Product> getById(Integer id);

    void save(Product product);

    void deleteById(Integer id);

    void updateBalance(Product product);

    List<Product> getProductByCLientId(Integer id);

}
