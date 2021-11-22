package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Product getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @PostMapping("/product")
    public void save(Product product){
        productService.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Integer id){
        productService.deleteById(id);
    }
}
