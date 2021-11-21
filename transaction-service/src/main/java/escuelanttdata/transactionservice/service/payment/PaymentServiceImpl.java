package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.dao.PaymentDao;
import escuelanttdata.transactionservice.model.payment.Payment;
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
