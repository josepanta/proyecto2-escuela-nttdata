package com.nttdata.featureclientservice.model;

import com.nttdata.featureclientservice.client.model.product.Product;
import com.nttdata.featureclientservice.client.model.product.Transaction;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    public Client(Integer id,String name,String numberDocument){
        this.setId(id);
        this.setName(name);
        this.setNumberDocument(numberDocument);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Column(name = "number_document")
    private String numberDocument;
    @Transient
    List<Transaction> transactions;
    @Transient
    List<Product> products;
}
