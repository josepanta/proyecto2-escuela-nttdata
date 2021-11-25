package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.repository.ProductRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Maybe<List<Product>> getAll() {

        return Maybe.fromCallable(() -> productRepository.findAll());
    }

    @Override
    public Single<Product> getById(Integer id) {

        return Single.fromCallable(() -> productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found")));
    }

    @Override
    public Completable save(Product product) {

        return Completable.fromCallable(() -> productRepository.save(product));
    }

    @Override
    public Completable deleteById(Integer id) {

        productRepository.deleteById(id);
        return Completable.fromCallable(Optional::empty);
    }

    @Override
    public Completable updateBalance(Product product) {

        return Optional.of(productRepository.getById(product.getId()))
                .map(productOptional -> {
                    productOptional.setBalance(productOptional.getBalance());
                    return Completable.fromCallable(() -> productRepository.save(productOptional));
                })
                .orElse(Completable.error(new Exception("Error")));
    }

    @Override
    public Maybe<List<Product>> getProductByClientId(Integer id) {

        return Maybe.fromCallable(() -> productRepository.findAll().stream()
                                            .filter(product -> product.getClientId()
                                            .equals(id)).collect(Collectors.toList()));
    }
}
