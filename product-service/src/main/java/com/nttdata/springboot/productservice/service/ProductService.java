package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Product;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface ProductService {
    Maybe<List<Product>> getAll();

    Single<Product> getById(Integer id);

    Completable save(Product product);

    Completable deleteById(Integer id);

    Completable updateBalance(Product product);

    Maybe<List<Product>> getProductByClientId(Integer id);

}
