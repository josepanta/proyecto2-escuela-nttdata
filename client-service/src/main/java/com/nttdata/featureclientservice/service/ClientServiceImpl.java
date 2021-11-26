package com.nttdata.featureclientservice.service;

import com.nttdata.featureclientservice.client.ProductClient;
import com.nttdata.featureclientservice.client.TransactionClient;
import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import com.nttdata.featureclientservice.repository.ClientRepository;
import com.nttdata.featureclientservice.model.Client;
import com.nttdata.featureclientservice.utils.exceptions.NotFoundException;
import com.nttdata.featureclientservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("clientService")
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TransactionClient transactionClient;

    @Autowired
    ProductClient productClient;


    @Override
    public Maybe<List<Client>> getAll() {

        return Maybe.fromCallable(() -> Optional.of(
                        clientRepository.findAll().stream()
                                .map(this::addProductToClient)
                                .collect(Collectors.toList()).
                                stream().
                                map(this::addTransactionToClient).
                                collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException("DB Access fail")));
    }

    @Override
    public Completable saveClient(Client client) {

        return Completable.fromCallable(() -> Optional.of(clientRepository.save(client)).orElseThrow(() -> new NotSavedException("Not Saved")));

    }

    @Override
    public Completable deletClient(Integer idClient) {

        return Completable.fromCallable(() -> clientRepository.findById(idClient).map(client -> {
            clientRepository.deleteById(idClient);
            return Optional.empty();
        }).orElseThrow(() -> new NotFoundException("Not Found")));
    }

    @Override
    public List<Product> getProductAll(Integer idClient) {
        return productClient.getProductAll()
                .stream()
                .filter(product -> product.getClientId().equals(idClient))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionByClientId(Integer idClient) {
        List<Product> products = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        products = productClient.getProductAll().stream().filter(productRow -> productRow.getClientId().equals(idClient)).collect(Collectors.toList());
        products.forEach(productRow ->
                transactionClient.getTransactionByProductId(productRow.getId()).forEach(transaction -> transactions.add(transaction))
        );
        return transactions;
    }

    @Override
    public Product getBalanceById(Integer id) {
        return productClient.getById(id);
    }

    private Client addProductToClient(Client client) {
        client.setProducts(productClient.getProductAll()
                .stream()
                .filter(product -> product.getClientId().equals(client.getId()))
                .collect(Collectors.toList()));
        return client;
    }

    private Client addTransactionToClient(Client client) {
        client.setTransactions(getTransactionByClientId(client.getId()));
        return client;
    }

}
