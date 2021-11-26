package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Product;
import com.nttdata.springboot.productservice.entity.ProductType;
import com.nttdata.springboot.productservice.repository.ProductRepository;
import com.nttdata.springboot.productservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Nested
    @DisplayName("Method: GetAll")
    class getAll {

        @Nested
        @DisplayName("Happy Path")
        class happyPath {

            @Test
            void test_getById_whenRepositoryReturnsAProduct() {

                Mockito.when(productRepository.findAll())
                        .thenReturn(buildListProducts());
                productService.getAll()
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

                Mockito.when(productRepository.findAll())
                        .thenReturn(new ArrayList<Product>());
                productService.getAll()
                        .test()
                        .assertComplete()
                        .assertValue(products -> products.size() == 0);
            }
        }

        private List<Product> buildListProducts() {
            return Arrays.asList(new Product(1, "123-456", new BigDecimal(550), "Active", new ProductType(), 1),
                    new Product(1, "123-456", new BigDecimal(550), "Active", new ProductType(), 1));
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

                Mockito.when(productRepository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.of(new Product()));
                productService.getById(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(product -> product.equals(new Product()));
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {

            @Test
            void test_getById_whenRepositoryNotFoundResourceById() {

                Mockito.when(productRepository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.empty());
                productService.getById(ArgumentMatchers.anyInt())
                        .test()
                        .assertNotComplete()
                        .assertError(NotFoundException.class)
                        .assertError(throwable -> throwable.getMessage().equals("Not Found"));
            }
        }
    }
}
