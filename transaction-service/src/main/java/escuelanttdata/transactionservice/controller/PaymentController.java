package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.Payment;
import escuelanttdata.transactionservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

  @PostMapping("/payment")
    public void savePayment(@RequestBody Payment payment){
        paymentService.save(payment);
    }

}
