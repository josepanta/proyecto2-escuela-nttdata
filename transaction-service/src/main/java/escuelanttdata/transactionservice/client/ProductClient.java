package escuelanttdata.transactionservice.client;

import escuelanttdata.transactionservice.client.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-product")
public interface ProductClient {

    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable Integer id);

    @PatchMapping("/product")
    void updateProduct(@PathVariable Product product);
}
