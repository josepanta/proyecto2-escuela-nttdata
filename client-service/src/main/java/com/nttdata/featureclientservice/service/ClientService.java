package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import com.nttdata.featureclientservice.model.Client;
import io.reactivex.Maybe;

import java.util.List;

public interface ClientService {
    public Maybe<List<Client>> getAll();

    public String saveClient(Client client);

    public List<Product> getProductAll(Integer idClient);

    public List<Transaction> getTransactionByClientId(Integer idClient);

    public Product getBalanceById(Integer id);
}
