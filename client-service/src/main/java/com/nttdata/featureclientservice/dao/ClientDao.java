package com.nttdata.featureclientservice.dao;

import com.nttdata.featureclientservice.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client,Integer> {

}
