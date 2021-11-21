package escuelanttdata.transactionservice.model.transaction;

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
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount")
    @NotNull(message = "the amount not null")
    private BigDecimal amount;

    @Column(name = "typeTransaction")
    private String typeTransaction;

    @CreationTimestamp
    @Column(name = "dateTime")
    private Date dateTime;

    @Column(name = "accountId")
    @NotNull(message = "the accountId not null")
    private Integer accountId;
}
