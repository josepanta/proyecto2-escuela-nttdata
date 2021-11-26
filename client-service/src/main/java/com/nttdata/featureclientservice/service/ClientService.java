package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import com.nttdata.featureclientservice.model.Client;
import io.reactivex.Completable;
import io.reactivex.Maybe;

import java.util.List;

public interface ClientService {
    Maybe<List<Client>> getAll();

    Completable saveClient(Client client);

    Completable deletClient(Integer idClient);

    List<Product> getProductAll(Integer idClient);

    List<Transaction> getTransactionByClientId(Integer idClient);

    Product getBalanceById(Integer id);
}
