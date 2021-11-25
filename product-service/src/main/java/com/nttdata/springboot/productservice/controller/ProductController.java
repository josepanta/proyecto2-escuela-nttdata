package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.service.ProductService;
import com.nttdata.springboot.productservice.utils.exceptions.NotFountException;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public Maybe<ResponseEntity<List<Product>>> getAll(){

        return productService.getAll()
                .map(listProducts -> ResponseEntity.status(HttpStatus.OK).body(listProducts));
    }

    @GetMapping("/product/{id}")
    public  Maybe<ResponseEntity<Object>> getById(@PathVariable Integer id){
        return   productService.getById(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body((Object) product))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @PostMapping("/product")
    public void save(Product product){
        productService.save(product);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Integer id){
        productService.deleteById(id);
    }

    @PutMapping("/product")
    public void update(@RequestBody Product product){
        productService.save(product);
    }

    @GetMapping("product/client/{id}")
    public List<Product> getProductByCLientId(@PathVariable Integer id){
        return productService.getProductByCLientId(id) ;
    }

    private Single<ResponseEntity<Object>> buildError(Throwable error){
        if(error.getClass()== NotFountException.class) return Single.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage()));
        else return Single.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Interno"));
    }
}
