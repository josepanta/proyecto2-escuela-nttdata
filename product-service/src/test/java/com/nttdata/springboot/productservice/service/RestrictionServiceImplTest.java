package com.nttdata.springboot.productservice.service;

import com.nttdata.springboot.productservice.entity.Restriction;
import com.nttdata.springboot.productservice.repository.RestrictionRepository;
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

public class RestrictionServiceImplTest {

    @Mock
    RestrictionRepository RestrictionRepository;

    @InjectMocks
    RestrictionServiceImpl RestrictionServiceImpl;

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

                Mockito.when(RestrictionRepository.findAll())
                        .thenReturn(buildListProducts());
                RestrictionServiceImpl.getAll()
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

                Mockito.when(RestrictionRepository.findAll())
                        .thenReturn(new ArrayList<Restriction>());
                RestrictionServiceImpl.getAll()
                        .test()
                        .assertComplete()
                        .assertValue(products -> products.size() == 0);
            }
        }

        private List<Restriction> buildListProducts() {
            return Arrays.asList(new Restriction(1, "11000", "MaxCredit", "maximum amount of credit."),
                    new Restriction(1, "true", "FreeCommission", "free commission for a account."));
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

                Mockito.when(RestrictionRepository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.of(new Restriction()));
                RestrictionServiceImpl.getById(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(restriction -> restriction.equals(new Restriction()));
            }
        }

        @Nested
        @DisplayName("Unhappy Path")
        class unHappyPath {

            @Test
            void test_getById_whenRepositoryNotFoundResourceById() {

                Mockito.when(RestrictionRepository.findById(ArgumentMatchers.anyInt()))
                        .thenReturn(Optional.empty());
                RestrictionServiceImpl.getById(ArgumentMatchers.anyInt())
                        .test()
                        .assertNotComplete()
                        .assertError(NotFoundException.class)
                        .assertError(throwable -> throwable.getMessage().equals("Not Found"));
            }
        }
    }
}
