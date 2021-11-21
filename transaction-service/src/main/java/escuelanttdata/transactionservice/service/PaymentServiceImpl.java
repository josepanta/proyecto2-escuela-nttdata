package escuelanttdata.transactionservice.service;

import escuelanttdata.transactionservice.dao.PaymentDao;
import escuelanttdata.transactionservice.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentDao paymentDao;


   @Override
    public void save(Payment payment) {
        paymentDao.save(payment);
    }
}
