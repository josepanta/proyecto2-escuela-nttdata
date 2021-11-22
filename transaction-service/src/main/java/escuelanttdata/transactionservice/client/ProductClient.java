package escuelanttdata.transactionservice.client;

import escuelanttdata.transactionservice.client.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "service-product")
public interface ProductClient {

    @GetMapping("/product/{id}")
    Product getById(@PathVariable Integer id);

    @PutMapping("/product")
    void updateProduct(@RequestBody Product product);
}
