package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Restriction;
import com.nttdata.springboot.productservice.repository.RestrictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestrictionServiceImpl implements RestrictionService{

    @Autowired
    private RestrictionRepository restrictionRepository;

    @Override
    public List<Restriction> getAll() {
        return restrictionRepository.findAll();
    }

    @Override
    public Restriction getById(Integer id) {
        return restrictionRepository.findById(id).get();
    }

    @Override
    public void save(Restriction restriction) {
        restrictionRepository.save(restriction);
    }

    @Override
    public void deleteById(Integer id) {
        restrictionRepository.deleteById(id);
    }
}
