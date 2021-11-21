package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Restriction;

import java.util.List;

public interface RestrictionService {

    public List<Restriction> getAll();

    public Restriction getById(Integer id);

    public void save(Restriction restriction);

    public void deleteById(Integer id);
}
