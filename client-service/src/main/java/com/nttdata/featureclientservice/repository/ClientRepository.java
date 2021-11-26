package com.nttdata.featureclientservice.repository;

import com.nttdata.featureclientservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
