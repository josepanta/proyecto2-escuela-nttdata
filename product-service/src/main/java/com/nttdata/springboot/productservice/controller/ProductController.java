package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.service.ProductService;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import com.nttdata.springboot.productservice.utils.exceptions.NotSavedException;
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
    public Maybe<ResponseEntity<Object>> getAll() {

        return productService.getAll()
                .map(listProducts -> ResponseEntity.status(HttpStatus.OK).body((Object) listProducts))
                .toSingle()
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @GetMapping("/{id}")
    public Maybe<ResponseEntity<Object>> getById(@PathVariable Integer id) {

        return productService.getById(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body((Object) product))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @PostMapping
    public Maybe<ResponseEntity<Object>> save(@RequestBody Product product) {

        return productService.save(product)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body((Object) product))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @DeleteMapping("/{id}")
    public Maybe<ResponseEntity<Object>> delete(@PathVariable Integer id) {

        return productService.deleteById(id)
                .toSingle(() -> ResponseEntity.status(HttpStatus.OK).body((Object) id))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @PutMapping
    public Maybe<ResponseEntity<Object>> updateBalance(@RequestBody Product product) {

        return productService.save(product)
                .toSingle(() -> ResponseEntity.status(HttpStatus.OK).body((Object) product))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @GetMapping("/client/{id}")
    public Maybe<ResponseEntity<Object>> getProductByClientId(@PathVariable Integer id) {

        return productService.getProductByClientId(id)
                .map(products -> ResponseEntity.status(HttpStatus.OK).body((Object) products))
                .toSingle()
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    private Single<ResponseEntity<Object>> buildError(Throwable error) {

        if (error.getClass() == NotFoundException.class)
            return Single.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage()));
        else if (error.getClass() == NotSavedException.class)
            return Single.just(ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error.getMessage()));
        else return Single.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error"));
    }
}
