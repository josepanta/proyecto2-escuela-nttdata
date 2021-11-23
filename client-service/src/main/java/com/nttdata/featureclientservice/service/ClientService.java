package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import com.nttdata.featureclientservice.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClientService {
    public List<Client> getClientAll();
    public String saveClient(Client client);
    public List<Product> getProductAll(Integer idClient);
    public List<Transaction> getTransactionByProductId(Integer idClient);
    public Product getBalanceById(Integer id);
}
