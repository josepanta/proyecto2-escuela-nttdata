package escuelanttdata.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-product")
public interface ProductClient {

    @GetMapping("/product/{id}")
    List<Product> getProductById(@PathVariable Integer id);
}
