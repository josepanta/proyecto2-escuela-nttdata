package com.nttdata.featureclientservice.client.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductType {
    private Integer id;
    private String name;
    private String description;
}
