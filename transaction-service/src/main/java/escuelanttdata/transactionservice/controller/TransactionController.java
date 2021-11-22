package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.transaction.Transaction;
import escuelanttdata.transactionservice.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public void saveTransaction(@RequestBody Transaction transaction){
        transactionService.save(transaction);
    }

    @PostMapping("/savetransaction/{amount}/{typeTransaction}/{accountId}")
    public void saveTransactionForParam(@Valid @PathVariable BigDecimal amount,@Valid  @PathVariable String typeTransaction,@Valid @PathVariable Integer productId){
        transactionService.save(Transaction.builder()
                .amount(amount)
                .dateTime(new Date())
                .typeTransaction(typeTransaction)
                .productId(productId)
                .build());
    }

    @GetMapping("/transaction/account/{id}")
    public List<Transaction> getTransactionByAccountId(@PathVariable Integer id){
        return transactionService.getTransactionByProductId(id);
    }


}
