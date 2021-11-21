package escuelanttdata.transactionservice.dao;

import escuelanttdata.transactionservice.model.charge.Charge;
import org.springframework.data.repository.CrudRepository;

public interface ChargeDao extends CrudRepository<Charge,Integer> {
}
