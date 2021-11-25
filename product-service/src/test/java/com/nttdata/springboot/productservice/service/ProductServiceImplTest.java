package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.repository.ProductRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getById_whenNotFountResourceById(){
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.empty());
        productService.getById(1)
                .test()
                .assertNotComplete()
                .assertError(NotFoundException.class)
                .assertError(error -> ((NotFoundException)error).getMessage().equals("No se encontro el recurso"));
    }

}
