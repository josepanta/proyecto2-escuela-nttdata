package com.nttdata.springboot.productservice.repository;

import com.nttdata.springboot.productservice.entity.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Integer> {
}
