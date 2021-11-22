package com.nttdata.springboot.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numberProduct;
    private BigDecimal balance;
    private String state;
    private Integer typeProductId;
    private Integer clientId;


}
