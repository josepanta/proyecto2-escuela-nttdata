package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    public List<ProductType> getAll();

    public ProductType getById(Integer id);

    public void save(ProductType productType);

    public void deleteById(Integer id);
}
