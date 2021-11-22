package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/productType")
    public List<ProductType> getAll(){
        return productTypeService.getAll();
    }

    @GetMapping("/productType/{id}")
    public ProductType getById(@PathVariable Integer id){
        return productTypeService.getById(id);
    }

    @PostMapping("/productType")
    public void save(ProductType productType){
        productTypeService.save(productType);
    }

    @DeleteMapping("/productType/{id}")
    public void delete(@PathVariable Integer id){
        productTypeService.deleteById(id);
    }
}
