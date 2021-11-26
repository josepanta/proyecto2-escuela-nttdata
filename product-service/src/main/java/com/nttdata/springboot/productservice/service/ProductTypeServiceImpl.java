package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.repository.ProductTypeRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import com.nttdata.springboot.productservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public Maybe<List<ProductType>> getAll() {

        return Maybe.fromCallable(() -> Optional.of(productTypeRepository.findAll()).orElseThrow(() -> new NotFoundException("DB Access fail")));
    }

    @Override
    public Single<ProductType> getById(Integer id) {

        return Single.fromCallable(() -> productTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found")));
    }

    @Override
    public Completable save(ProductType productType) {

        return Completable.fromCallable(() -> Optional.of(productTypeRepository.save(productType)).orElseThrow(() -> new NotSavedException("Not Saved")));
    }

    @Override
    public Completable deleteById(Integer id) {

        productTypeRepository.deleteById(id);
        return Completable.fromCallable(() -> productTypeRepository.findById(id).map(productType -> {
            productTypeRepository.deleteById(id);
            return Optional.empty();
        }).orElseThrow(() -> new NotFoundException("Not Found")));
    }
}
