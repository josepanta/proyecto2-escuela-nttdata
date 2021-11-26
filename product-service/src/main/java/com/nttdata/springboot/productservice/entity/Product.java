package com.nttdata.springboot.productservice.entity;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 11, max = 11)
    private String numberProduct;

    @NotNull
    @Digits(integer = 30, fraction = 5)
    private BigDecimal balance;

    @NotEmpty
    private String state;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @NotNull
    private Integer clientId;
}
