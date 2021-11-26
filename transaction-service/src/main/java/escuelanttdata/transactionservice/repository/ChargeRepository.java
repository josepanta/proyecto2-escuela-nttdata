package escuelanttdata.transactionservice.repository;

import escuelanttdata.transactionservice.model.charge.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ChargeRepository extends JpaRepository<Charge,Integer> {
    Optional<List<Charge>> getChargeByProductId(@PathVariable Integer productId);
}
