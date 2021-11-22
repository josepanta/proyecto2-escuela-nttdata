package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.payment.Payment;
import escuelanttdata.transactionservice.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment")
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @PostMapping("/payment")
    public void savePayment(@RequestBody Payment payment){
        paymentService.save(payment);
    }

    @GetMapping("/payment/product/{id}")
    public List<Payment> getPaymentByProductId(@PathVariable Integer id){
        return paymentService.getPaymentByProductId(id);
    }

}
