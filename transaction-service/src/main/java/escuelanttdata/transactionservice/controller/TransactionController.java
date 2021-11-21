package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public void saveTransaction(@RequestBody Transaction transaction){
        transactionService.save(transaction);
    }

}
