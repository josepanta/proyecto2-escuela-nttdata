package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientService extends CrudRepository<Client,Integer> {
}
