package com.nttdata.featureclientservice.client.model.transaction.charge;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@Data
public class Charge {


    private Integer id;


    private BigDecimal amount;


    private Date dateCreate;


    private Date dateUpdate;


    private String description;

    private Integer productId;

}
