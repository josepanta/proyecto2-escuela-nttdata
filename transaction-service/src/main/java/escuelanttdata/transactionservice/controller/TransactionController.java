package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public void saveTransaction(@RequestBody Transaction transaction){
        transactionService.save(transaction);
    }

    @GetMapping("/transaction/account/{id}")
    public List<Transaction> getTransactionByAccountId(@PathVariable Integer id){
        return transactionService.getTransactionByAccountId(id);
    }


}
