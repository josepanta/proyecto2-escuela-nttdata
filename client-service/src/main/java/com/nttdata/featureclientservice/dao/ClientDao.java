package com.nttdata.featureclientservice.dao;

import com.nttdata.featureclientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Integer> {

}
