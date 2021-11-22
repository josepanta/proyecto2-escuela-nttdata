package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientService {
    public List<Client> getClientAll();
    public String saveClient(Client client);
}
