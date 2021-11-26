package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.service.ProductTypeServiceImpl;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductTypeControllerTest {
    @Mock
    ProductTypeServiceImpl productTypeService;

    @InjectMocks
    ProductTypeController productTypeController;

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

                Mockito.when(productTypeService.getAll())
                        .thenReturn(buildListProductTypes());
                productTypeController.getAll()
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
                Mockito.when(productTypeService.getAll())
                        .thenReturn(Maybe.error(new Exception("Internal Error")));
                productTypeController.getAll()
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        private Maybe<List<ProductType>> buildListProductTypes() {

            return Maybe.just(Arrays.asList(new ProductType(1, "savingAccount","Product of type SavingAccount."),
                    new ProductType(2, "currentAccount","Product of type CurrentAccount.")));
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
                Mockito.when(productTypeService.getById(ArgumentMatchers.anyInt()))
                        .thenReturn(buildProductType());
                productTypeController.getById(1)
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

                Mockito.when(productTypeService.getById(ArgumentMatchers.anyInt()))
                        .thenReturn(Single.error(new Exception("Internal Error")));
                productTypeController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);

            }

            @Test
            void test_getById_whenServiceReturnsNotFoundError() {

                Mockito.when(productTypeService.getById(1))
                        .thenReturn(Single.error(new NotFoundException("Not Found")));
                productTypeController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
            }
        }

        private Single<ProductType> buildProductType() {

            return Single.just(new ProductType(1, "savingAccount","Product of type SavingAccount."));
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
                Mockito.when(productTypeService.save(new ProductType())).thenReturn(buildProductType());
                productTypeController.save(new ProductType())
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
                Mockito.when(productTypeService.save(new ProductType()))
                        .thenReturn(Completable.error(new NotSavedException("Not Saved")));
                productTypeController.save(new ProductType())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
            }
        }

        private Completable buildProductType() {

            return Completable.fromCallable(() -> new ProductType(1, "savingAccount","Product of type SavingAccount."));
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
                Mockito.when(productTypeService.deleteById(ArgumentMatchers.anyInt()))
                        .thenReturn(Completable.fromCallable(Optional::empty));
                productTypeController.delete(ArgumentMatchers.anyInt())
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
                Mockito.when(productTypeService.deleteById(ArgumentMatchers.anyInt()))
                        .thenReturn(Completable.error(new NotFoundException("Not Found")));
                productTypeController.delete(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
            }
        }
    }
}
