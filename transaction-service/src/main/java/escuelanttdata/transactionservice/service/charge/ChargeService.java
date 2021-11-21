package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.model.charge.Charge;

import java.util.List;

public interface ChargeService {

    void save(Charge charge);

    List<Charge> getChargeByProductId(Integer id);
}
