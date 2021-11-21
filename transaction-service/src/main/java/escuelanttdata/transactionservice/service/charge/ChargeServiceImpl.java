package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.dao.ChargeDao;
import escuelanttdata.transactionservice.model.charge.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargeServiceImpl implements  ChargeService{

    @Autowired
    ChargeDao chargeDao;

    @Override
    public void save(Charge charge) {
        chargeDao.save(charge);
    }

    @Override
    public List<Charge> getChargeByAccountId(Integer id) {
        List<Charge> chargeList = new ArrayList<>();
        chargeDao.getChargeByAccountId(id).forEach(charge -> chargeList.add(charge));
        return chargeList;
    }
}
