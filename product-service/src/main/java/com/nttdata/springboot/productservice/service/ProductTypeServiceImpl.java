package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> getAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType getById(Integer id) {
        return productTypeRepository.findById(id).get();
    }

    @Override
    public void save(ProductType productType) {
        productTypeRepository.save(productType);
    }

    @Override
    public void deleteById(Integer id) {
        productTypeRepository.deleteById(id);
    }
}
