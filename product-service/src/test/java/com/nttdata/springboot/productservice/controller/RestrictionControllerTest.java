package com.nttdata.springboot.productservice.controller;

import com.nttdata.springboot.productservice.entity.Restriction;
import com.nttdata.springboot.productservice.service.RestrictionServiceImpl;
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

public class RestrictionControllerTest {
    @Mock
    RestrictionServiceImpl restrictionServiceImpl;

    @InjectMocks
    RestrictionController restrictionController;

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

                Mockito.when(restrictionServiceImpl.getAll())
                        .thenReturn(buildListRestrictions());
                restrictionController.getAll()
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
                Mockito.when(restrictionServiceImpl.getAll())
                        .thenReturn(Maybe.error(new Exception("Internal Error")));
                restrictionController.getAll()
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        private Maybe<List<Restriction>> buildListRestrictions() {

            return Maybe.just(Arrays.asList(new Restriction(1, "11000", "MaxCredit", "maximum amount of credit."),
                    new Restriction(1, "true", "FreeCommission", "free commission for a account.")));
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
                Mockito.when(restrictionServiceImpl.getById(ArgumentMatchers.anyInt()))
                        .thenReturn(buildRestriction());
                restrictionController.getById(1)
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

                Mockito.when(restrictionServiceImpl.getById(ArgumentMatchers.anyInt()))
                        .thenReturn(Single.error(new Exception("Internal Error")));
                restrictionController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR);

            }

            @Test
            void test_getById_whenServiceReturnsNotFoundError() {

                Mockito.when(restrictionServiceImpl.getById(1))
                        .thenReturn(Single.error(new NotFoundException("Not Found")));
                restrictionController.getById(1)
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
            }
        }

        private Single<Restriction> buildRestriction() {

            return Single.just(new Restriction(1, "11000", "MaxCredit", "maximum amount of credit."));
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
                Mockito.when(restrictionServiceImpl.save(new Restriction())).thenReturn(buildRestriction());
                restrictionController.save(new Restriction())
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
                Mockito.when(restrictionServiceImpl.save(new Restriction()))
                        .thenReturn(Completable.error(new NotSavedException("Not Saved")));
                restrictionController.save(new Restriction())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_ACCEPTABLE);
            }
        }

        private Completable buildRestriction() {

            return Completable.fromCallable(() -> new Restriction(1, "11000", "MaxCredit", "maximum amount of credit."));
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
                Mockito.when(restrictionServiceImpl.deleteById(ArgumentMatchers.anyInt()))
                        .thenReturn(Completable.fromCallable(Optional::empty));
                restrictionController.delete(ArgumentMatchers.anyInt())
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
                Mockito.when(restrictionServiceImpl.deleteById(ArgumentMatchers.anyInt()))
                        .thenReturn(Completable.error(new NotFoundException("Not Found")));
                restrictionController.delete(ArgumentMatchers.anyInt())
                        .test()
                        .assertComplete()
                        .assertNoErrors()
                        .assertValue(responseEntity -> responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
            }
        }
    }
}
