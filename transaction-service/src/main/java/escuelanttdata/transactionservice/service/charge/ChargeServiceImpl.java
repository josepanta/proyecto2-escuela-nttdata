package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.dao.ChargeDao;
import escuelanttdata.transactionservice.model.charge.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeServiceImpl implements  ChargeService{

    @Autowired
    ChargeDao chargeDao;

    @Override
    public void save(Charge charge) {
        chargeDao.save(charge);
    }
}
