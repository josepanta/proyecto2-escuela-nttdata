package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.dao.ClientDao;
import com.nttdata.featureclientservice.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("clientService")
public class ClientServiceImpl implements ClientService{
@Autowired
    ClientDao clientDao;


    @Override
    public List<Client> getClientAll() {
        List<Client> data = new ArrayList<>();
        clientDao.findAll().forEach(client -> data.add(client));
        return data;
    }

    @Override
    public String saveClient(Client client) {
    clientDao.save(client);
    return "Insertado correctamente";
    }

}
