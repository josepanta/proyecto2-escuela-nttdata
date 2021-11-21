package escuelanttdata.transactionservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {
    private Integer id;
    private String numberProduct;
    private BigDecimal balance;
    private String state;
    private Integer typeProductId;
    private Integer clientId;
}
