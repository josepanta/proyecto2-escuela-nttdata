package com.nttdata.featureclientservice.controller;

import com.nttdata.featureclientservice.model.Client;
import com.nttdata.featureclientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    @Qualifier("clientServiceImpl")
    ClientService clientServiceImpl;

    @GetMapping("getClient")
    public List<Client> getClientAll(){
        return clientServiceImpl.getClientAll();
    }

    /*@GetMapping('saveClient')
    public String saveClient(@RequestBody Client client){
        return ResponseEntity.ok();
    }*/




}
