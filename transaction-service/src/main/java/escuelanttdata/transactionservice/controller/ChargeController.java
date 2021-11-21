package escuelanttdata.transactionservice.controller;

import escuelanttdata.transactionservice.model.charge.Charge;
import escuelanttdata.transactionservice.service.charge.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChargeController {

    @Autowired
    ChargeService chargeService;

    @PostMapping("/charge")
    public void saveCharge(@RequestBody Charge charge){
        chargeService.save(charge);
    }

    @GetMapping("/charge/account/{id}")
    public List<Charge> getTransactionByAccountId(@PathVariable Integer id){
        return chargeService.getChargeByProductId(id);
    }
}