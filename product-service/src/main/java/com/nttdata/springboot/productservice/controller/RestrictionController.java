package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Restriction;
import com.nttdata.springboot.productservice.service.RestrictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestrictionController {

    @Autowired
    private RestrictionService restrictionService;

    @GetMapping("/restriction")
    public List<Restriction> getAll(){
        return restrictionService.getAll();
    }

    @GetMapping("/restriction/{id}")
    public Restriction getById(@PathVariable Integer id){
        return restrictionService.getById(id);
    }

    @PostMapping("/restriction")
    public void save(Restriction restriction){
        restrictionService.save(restriction);
    }

    @DeleteMapping("/restriction/{id}")
    public void delete(@PathVariable Integer id){
        restrictionService.deleteById(id);
    }
}
