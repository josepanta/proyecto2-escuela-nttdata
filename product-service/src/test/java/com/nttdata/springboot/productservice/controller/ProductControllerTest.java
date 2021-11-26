package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.service.ProductServiceImpl;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ProductControllerTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_getAll_whenServiceReturnsAListProducts() {
        Mockito.when(productService.getAll()).thenReturn(buildListProducts());
        productController.getAll()
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue( listResponseEntity -> Stream.of(listResponseEntity.getBody()).collect(Collectors.toList()).size() == 1 && listResponseEntity.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void test_getById_whenServiceRetursAProduct() {
        Mockito.when(productService.getById(ArgumentMatchers.anyInt())).thenReturn(buildProduct());
        productController.getById(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(productResponseEntity -> productResponseEntity.getStatusCode()==HttpStatus.OK
                        && ((Product)productResponseEntity.getBody()).getNumberProduct().equals(buildProduct().blockingGet().getNumberProduct()));
    }

    @Test
    void test_getById_whenServiceReturnsErrorDB(){
        Mockito.when(productService.getById(1)).thenReturn(Single.error(new Exception("DB fail")));
        productController.getById(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(responseEntity -> responseEntity.getStatusCode()==HttpStatus.INTERNAL_SERVER_ERROR
                && ((String)responseEntity.getBody()).equals("Error Interno"));
    }

    @Test
    void test_getById_whenServiceReturnsNotFountError(){
        Mockito.when(productService.getById(1)).thenReturn(Single.error(new NotFoundException("not fount")));
        productController.getById(1)
                .test()
                .assertComplete()
                .assertNoErrors()
                .assertValue(responseEntity -> responseEntity.getStatusCode()==HttpStatus.NOT_FOUND
                && responseEntity.getBody().equals("not fount"));
    }

    private Maybe<List<Product>> buildListProducts() {
        return Maybe.just(Arrays.asList(new Product(1, "123-456", new BigDecimal(550), "Active", new ProductType(), 1)));
    }

    private Single<Product> buildProduct() {
        return  Single.just(new Product(1, "123-789", new BigDecimal(700), "Active", new ProductType(), 2));
    }


}
