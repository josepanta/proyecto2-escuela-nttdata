package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(Integer id);

    void save(Product product);

    void deleteById(Integer id);

    void updateBalance(Product product);

}
