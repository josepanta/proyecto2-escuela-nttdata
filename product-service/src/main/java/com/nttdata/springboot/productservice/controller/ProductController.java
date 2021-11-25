package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.service.ProductService;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Maybe<ResponseEntity<List<Product>>> getAll() {

        return productService.getAll()
                .map(listProducts -> ResponseEntity.status(HttpStatus.OK).body(listProducts));
    }

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<Object>> getById(@PathVariable Integer id) {

        return productService.getById(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body((Object) product))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @PostMapping
    public Maybe<ResponseEntity<Product>> save(@RequestBody Product product) {

        return productService.save(product)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body(product))
                .toMaybe();
    }

    @DeleteMapping("/{id}")
    public Maybe<ResponseEntity<Integer>> delete(@PathVariable Integer id) {

        return productService.deleteById(id)
                .toSingle(() -> ResponseEntity.status(HttpStatus.OK).body(id))
                .toMaybe();
    }

    @PutMapping
    public Maybe<ResponseEntity<Product>> updateBalance(@RequestBody Product product) {

        return productService.save(product)
                .toSingle(() -> ResponseEntity.status(HttpStatus.OK).body(product))
                .toMaybe();
    }

    @GetMapping("/client/{id}")
    public Maybe<ResponseEntity<List<Product>>> getProductByClientId(@PathVariable Integer id) {

        return productService.getProductByClientId(id)
                .map(products -> ResponseEntity.status(HttpStatus.OK).body(products));
    }

    private Single<ResponseEntity<Object>> buildError(Throwable error) {

        if (error.getClass() == NotFoundException.class)
            return Single.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage()));
        else return Single.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error"));
    }
}
