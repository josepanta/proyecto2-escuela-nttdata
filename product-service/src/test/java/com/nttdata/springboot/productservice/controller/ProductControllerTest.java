package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.service.ProductServiceImpl;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import com.nttdata.springboot.productservice.utils.exceptions.NotSavedException;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class ProductControllerTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("Method: GetAll")
    class getAll {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_getAll_whenServiceReturnsAListProducts() {

                Mockito.when(productService.getAll())
                        .thenReturn(buildListProducts());
                productController.getAll()
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(listResponseEntity -> listResponseEntity.getStatusCode() == HttpStatus.OK);
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {
            @Test
            void test_getAll_whenServiceReturnNotFoundError() {
                Mockito.when(productService.getAll())
                        .thenReturn(Maybe.error(new Exception("Internal Error")));
                productController.getAll()
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        private Maybe<List<Product>> buildListProducts() {

            return Maybe.just(Arrays.asList(new Product(1, "123-456", new BigDecimal(550), "Active", new ProductType(), 1),
                    new Product(1, "123-456", new BigDecimal(550), "Active", new ProductType(), 1)));
        }
    }

    @Nested
    @DisplayName("Method: GetById")
    class getById {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_getById_whenServiceReturnsAProduct() {
                Mockito.when(productService.getById(ArgumentMatchers.anyInt()))
                        .thenReturn(buildProduct());
                productController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(productResponseEntity -> productResponseEntity.getStatusCode() == HttpStatus.OK);
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {

            @Test
            void test_getById_whenServiceReturnsErrorDB() {

                Mockito.when(productService.getById(ArgumentMatchers.anyInt()))
                        .thenReturn(Single.error(new Exception("Internal Error")));
                productController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);

            }

            @Test
            void test_getById_whenServiceReturnsNotFoundError() {

                Mockito.when(productService.getById(1))
                        .thenReturn(Single.error(new NotFoundException("Not Found")));
                productController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
            }
        }

        private Single<Product> buildProduct() {

            return Single.just(new Product(1, "123-789", new BigDecimal(700), "Active", new ProductType(), 2));
        }
    }

    @Nested
    @DisplayName("Method: Save")
    class save {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_save_whenServiceSaveProduct() {
                Mockito.when(productService.save(new Product())).thenReturn(buildProduct());
                productController.save(new Product())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(objectResponseEntity -> objectResponseEntity.getStatusCode() == HttpStatus.CREATED);
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {
            @Test
            void test_save_whenServiceReturnNotSaved(){
                Mockito.when(productService.save(new Product()))
                        .thenReturn(Completable.error(new NotSavedException("Not Saved")));
                productController.save(new Product())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
            }
        }

        private Completable buildProduct() {

            return Completable.fromCallable(() -> new Product(1, "123-789", new BigDecimal(700), "Active", new ProductType(), 2));
        }
    }

    @Nested
    @DisplayName("Method: Delete")
    class delete {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_delete_whenServiceDelete(){
                Mockito.when(productService.deleteById(ArgumentMatchers.anyInt()))
                        .thenReturn(Completable.fromCallable(Optional::empty));
                productController.delete(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.OK);
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {
            @Test
            void test_delete_whenServiceNotDelete(){
                Mockito.when(productService.deleteById(ArgumentMatchers.anyInt()))
                        .thenReturn(Completable.error(new NotFoundException("Not Found")));
                productController.delete(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
            }
        }
    }
}
