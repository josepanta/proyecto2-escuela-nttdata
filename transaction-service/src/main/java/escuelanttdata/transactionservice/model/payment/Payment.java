package escuelanttdata.transactionservice.model.payment;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "date_Create")
    private Date dateCreate;

    @CreationTimestamp
    @Column(name = "date_Update")
    private Date dateUpdate;

    @NotNull
    @Column(name = "product_Id")
    private Integer productId;

}
