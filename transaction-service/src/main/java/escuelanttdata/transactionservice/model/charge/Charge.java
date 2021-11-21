package escuelanttdata.transactionservice.model.charge;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
@Entity
@Table(name = "charge")
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "dateCreate")
    private Date dateCreate;

    @CreationTimestamp
    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "description")
    private String description;

    @Column(name = "accountId")
    private Integer accountId;

}
