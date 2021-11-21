package escuelanttdata.transactionservice.model;

import lombok.*;
import javax.persistence.*;
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

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "accountId")
    private Integer accountId;

}
