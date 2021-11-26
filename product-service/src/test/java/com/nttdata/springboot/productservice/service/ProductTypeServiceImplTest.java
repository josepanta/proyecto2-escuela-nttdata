package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.repository.ProductTypeRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductTypeServiceImplTest {

    @Mock
    ProductTypeRepository productTypeRepository;

    @InjectMocks
    ProductTypeServiceImpl productTypeServiceImpl;

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
            void test_getById_whenRepositoryReturnsAProduct() {

                Mockito.when(productTypeRepository.findAll())
                        .thenReturn(buildListProducts());
                productTypeServiceImpl.getAll()
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(products -> products.size() == 2);
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {

            @Test
            void test_getById_whenRepositoryNotFoundProducts() {

                Mockito.when(productTypeRepository.findAll())
                        .thenReturn(new ArrayList<ProductType>());
                productTypeServiceImpl.getAll()
                        .test()
                        .assertComplete()
                        .assertValue(products -> products.size() == 0);
            }
        }

        private List<ProductType> buildListProducts() {
            return Arrays.asList(new ProductType(1, "savingAccount","Product of type SavingAccount."),
                    new ProductType(2, "currentAccount","Product of type CurrentAccount."));
        }
    }

    @Nested
    @DisplayName("Method: GetById")
    class getById {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_getById_whenRepositoryReturnsAProduct() {

                Mockito.when(productTypeRepository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.of(new ProductType()));
                productTypeServiceImpl.getById(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(productType -> productType.equals(new ProductType()));
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {

            @Test
            void test_getById_whenRepositoryNotFoundResourceById() {

                Mockito.when(productTypeRepository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.empty());
                productTypeServiceImpl.getById(ArgumentMatchers.anyInt())
                        .test()
                        .assertNotComplete()
                        .assertError(NotFoundException.class)
                        .assertError(throwable -> throwable.getMessage().equals("Not Found"));
            }
        }
    }
}
