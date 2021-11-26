package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Restriction;
import com.nttdata.springboot.productservice.repository.RestrictionRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import com.nttdata.springboot.productservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestrictionServiceImpl implements RestrictionService {

    @Autowired
    private RestrictionRepository restrictionRepository;

    @Override
    public Maybe<List<Restriction>> getAll() {

        return Maybe.fromCallable(() -> Optional.of(restrictionRepository.findAll()).orElseThrow(() -> new NotFoundException("DB Access fail")));
    }

    @Override
    public Single<Restriction> getById(Integer id) {

        return Single.fromCallable(() -> restrictionRepository.findById(id).orElseThrow(() -> new Exception("Not Found")));
    }

    @Override
    public Completable save(Restriction restriction) {

        return Completable.fromCallable(() -> Optional.of(restrictionRepository.save(restriction)).orElseThrow(() -> new NotSavedException("Not Saved")));
    }

    @Override
    public Completable deleteById(Integer id) {

        return Completable.fromCallable(() -> restrictionRepository.findById(id).map(product -> {
            restrictionRepository.deleteById(id);
            return Optional.empty();
        }).orElseThrow(() -> new NotFoundException("Not Found")));
    }
}
