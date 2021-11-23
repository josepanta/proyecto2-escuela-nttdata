package com.nttdata.featureclientservice.client.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {
    private Integer id;
    private String numberProduct;
    private BigDecimal balance;
    private String state;
    private ProductType productType;
    private Integer clientId;
}
