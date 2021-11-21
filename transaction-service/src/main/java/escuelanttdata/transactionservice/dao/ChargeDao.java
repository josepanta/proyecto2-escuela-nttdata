package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.charge.Charge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ChargeDao extends CrudRepository<Charge,Integer> {

    List<Charge> getChargeByAccountId(@PathVariable Integer accountId);
}
