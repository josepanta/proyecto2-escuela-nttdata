package escuelanttdata.transactionservice.model.charge;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @Digits(integer = 30, fraction = 5)
    @NotNull
    @Column(name = "amount")
    private BigDecimal amount;

    @CreationTimestamp
    @Column(name = "dateCreate")
    private Date dateCreate;

    @CreationTimestamp
    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "productId")
    private Integer productId;

}
