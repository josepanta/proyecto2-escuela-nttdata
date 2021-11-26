package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.ProductType;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface ProductTypeService {

    public Maybe<List<ProductType>> getAll();

    public Single<ProductType> getById(Integer id);

    public Completable save(ProductType productType);

    public Completable deleteById(Integer id);
}
