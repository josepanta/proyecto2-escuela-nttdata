package escuelanttdata.transactionservice.service.charge;

import escuelanttdata.transactionservice.client.ProductClient;
import escuelanttdata.transactionservice.client.model.Product;
import escuelanttdata.transactionservice.dao.ChargeDao;
import escuelanttdata.transactionservice.model.charge.Charge;
import escuelanttdata.transactionservice.model.transaction.TypeTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChargeServiceImpl implements  ChargeService{

    @Autowired
    ProductClient productClient;

    @Autowired
    ChargeDao chargeDao;

    @Override
    public void save(Charge charge) {

        Product product;
        product=productClient.getById(charge.getProductId());
        product.setBalance(product.getBalance().subtract(charge.getAmount()));
        productClient.updateProduct(product);
        chargeDao.save(charge);

    }

    @Override
    public List<Charge> getChargeByProductId(Integer id) {
        List<Charge> chargeList = new ArrayList<>();
        chargeDao.getChargeByProductId(id).forEach(charge -> chargeList.add(charge));
        return chargeList;
    }
}
