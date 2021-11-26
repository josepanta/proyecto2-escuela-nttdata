package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Restriction;
import com.nttdata.springboot.productservice.service.RestrictionService;
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
import java.util.List;

@RestController
@Validated
public class RestrictionController {

    @Autowired
    private RestrictionService restrictionService;

    @GetMapping("/restriction")
    public Maybe<ResponseEntity<Object>> getAll() {

        return restrictionService.getAll()
                .map(restrictions -> ResponseEntity.status(HttpStatus.OK).body((Object) restrictions))
                .toSingle()
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @GetMapping("/restriction/{id}")
    public Maybe<ResponseEntity<Object>> getById(@PathVariable @Min(1) Integer id) {

        return restrictionService.getById(id)
                .map(restriction -> ResponseEntity.status(HttpStatus.OK).body((Object) restriction))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @PostMapping("/restriction")
    public Maybe<ResponseEntity<Object>> save(@Valid @RequestBody Restriction restriction) {

        return restrictionService.save(restriction)
                .toSingle(() -> ResponseEntity.status(HttpStatus.OK).body((Object) "Created Restriction"))
                .onErrorResumeNext(this::buildError)
                .toMaybe();
    }

    @DeleteMapping("/restriction/{id}")
    public Maybe<ResponseEntity<Object>> delete(@PathVariable @Min(1) Integer id) {

        return restrictionService.deleteById(id)
                .toSingle(() -> ResponseEntity.status(HttpStatus.CREATED).body((Object) "Deleted Product"))
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
