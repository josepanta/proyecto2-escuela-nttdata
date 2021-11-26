package com.nttdata.featureclientservice.controller;

import com.nttdata.featureclientservice.model.Client;
import com.nttdata.featureclientservice.service.ClientServiceImpl;
import io.reactivex.Completable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class ClientServiceTest {
    @Mock
    ClientServiceImpl clientService;

    @InjectMocks
    ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient_whenServiceSaveClient() {
        Mockito.when(clientService.saveClient(new Client())).thenReturn(buildClient());
        clientController.saveClient(new Client())
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(objectResponseEntity -> objectResponseEntity.getStatusCode() == HttpStatus.CREATED);
    }

    private Completable buildClient() {

        return Completable.fromCallable(() -> new Client(1, "Juan Perez Carpio","123-789-123"));
    }

}
