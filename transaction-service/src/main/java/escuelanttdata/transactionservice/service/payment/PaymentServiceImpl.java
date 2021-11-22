package escuelanttdata.transactionservice.service.payment;

import escuelanttdata.transactionservice.client.ProductClient;
import escuelanttdata.transactionservice.client.model.Product;
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
    ProductClient productClient;

    @Autowired
    PaymentDao paymentDao;


    @Override
    public List<Payment> getAll() {
        List<Payment> paymentList = new ArrayList<>();
        paymentDao.findAll().forEach(payment -> paymentList.add(payment));
        return  paymentList;
    }

    @Override
    public void save(Payment payment) {
        Product product;
        product=productClient.getById(payment.getProductId());
        product.setBalance(product.getBalance().add(payment.getAmount()));
        productClient.updateProduct(product);
        paymentDao.save(payment);
    }

    @Override
    public List<Payment> getPaymentByProductId(Integer id) {
       List<Payment> paymentList = new ArrayList<>();
        paymentDao.getPaymentByProductId(id).forEach(payment -> paymentList.add(payment));
        return  paymentList;
    }
}
