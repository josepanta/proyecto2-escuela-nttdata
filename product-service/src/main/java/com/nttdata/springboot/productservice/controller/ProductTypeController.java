package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.service.ProductTypeService;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import com.nttdata.springboot.productservice.utils.exceptions.NotSavedException;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/productType")
    public Maybe<ResponseEntity<Object>> getAll(){
        return productTypeService.getAll()
                .map(productTypes -> ResponseEntity.status(HttpStatus.OK).body((Object) productTypes))
                .toSingle()
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @GetMapping("/productType/{id}")
    public Maybe<ResponseEntity<Object>> getById(@PathVariable @Min(1) Integer id){
        return productTypeService.getById(id)
                .map(productType -> ResponseEntity.status(HttpStatus.OK).body((Object) productType))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @PostMapping("/productType")
    public Maybe<ResponseEntity<Object>> save(@Valid @RequestBody ProductType productType){
        return productTypeService.save(productType)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body((Object) "Created ProductType"))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @DeleteMapping("/productType/{id}")
    public Maybe<ResponseEntity<Object>> delete(@PathVariable @Min(1) Integer id){
        return productTypeService.deleteById(id)
                .toSingle(() -> ResponseEntity.status(HttpStatus.OK).body((Object) "Deleted ProductType"))
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
