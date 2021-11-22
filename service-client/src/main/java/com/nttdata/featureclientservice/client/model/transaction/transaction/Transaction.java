package com.nttdata.featureclientservice.client.model.transaction.transaction;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

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
