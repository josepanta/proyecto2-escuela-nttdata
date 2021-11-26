package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Restriction;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface RestrictionService {

    public Maybe<List<Restriction>> getAll();

    public Single<Restriction> getById(Integer id);

    public Completable save(Restriction restriction);

    public Completable deleteById(Integer id);
}
