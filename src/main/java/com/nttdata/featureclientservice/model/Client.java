package com.nttdata.featureclientservice.model;

import lombok.Builder;
import lombok.Data;


import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String numberDocument;

}
