package escuelanttdata.transactionservice.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {
    private Integer id;
    private String numberProduct;
    private BigDecimal balance;
    private String state;
    private ProductType productType;
    private Integer clientId;
}
