package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.repository.ProductRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFountException;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Maybe<List<Product>> getAll() {
        return  Maybe.fromCallable(() -> productRepository.findAll());
    }

    @Override
    public Single<Product> getById(Integer id) {
        return Single.fromCallable(() -> productRepository.findById(id).orElseThrow(()-> new NotFountException("No se encontro el recurso")));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateBalance(Product product) {
        Product productDB = productRepository.getById(product.getId());
        productDB.setBalance(product.getBalance());
        productRepository.save(productDB);
    }

    @Override
    public List<Product> getProductByCLientId(Integer id) {
        return productRepository.findAll().stream().filter(product -> product.getClientId().equals(id)).collect(Collectors.toList());
    }
}
