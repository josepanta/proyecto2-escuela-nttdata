package com.nttdata.featureclientservice.client.model.product;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Data
public class Transaction {

    private Integer id;

    private BigDecimal amount;


    private String typeTransaction;


    private Date dateTime;


    private Integer productId;
}

