package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.dao.PaymentDao;
import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentDao paymentDao;


   @Override
    public void save(Payment payment) {
        paymentDao.save(payment);
    }

    @Override
    public List<Payment> getPaymentByAccountId(Integer id) {
       List<Payment> paymentList = new ArrayList<>();
        paymentDao.getPaymentByAccountId(id).forEach(payment -> paymentList.add(payment));
        return  paymentList;
    }
}
