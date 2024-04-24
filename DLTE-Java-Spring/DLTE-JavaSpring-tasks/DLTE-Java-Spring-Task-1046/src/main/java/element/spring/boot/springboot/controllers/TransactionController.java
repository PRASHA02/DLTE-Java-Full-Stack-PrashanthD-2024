package element.spring.boot.springboot.controllers;

import element.spring.boot.springboot.model.TransactionEntity;
import element.spring.boot.springboot.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    //creating a new transaction using xml
    //url - http://localhost:8082/transaction/
//    <transaction>
//    <transactionId>77</transactionId>
//    <username>vignesh</username>
//    <accountId>123678789</accountId>
//    <amount>310000</amount>
//    <transactionType>withdraw</transactionType>
//    </transaction>
    @PostMapping(value = "/", consumes = "application/xml")
    public TransactionEntity callApiInsert(@RequestBody TransactionEntity transactionEntity) {
        return transactionService.callSave(transactionEntity);
    }


    //finding all object based on  user and type
    //url:- http://localhost:8082/transaction/findUserType/prashanth/deposit
//    [
//    {
//        "transactionId": 777,
//            "username": "prashanth",
//            "accountId": 12345678789,
//            "amount": 3728832,
//            "transactionType": "deposit"
//    }
//    ]
    @GetMapping("/findUserType/{user}/{type}")
    public List<TransactionEntity> callApiUserType(@PathVariable("user") String user, @PathVariable("type") String type) {
        return transactionService.callFindUserAndType(user, type);
    }

    // url:- http://localhost:8082/transaction/findByRange/1000/40000000
//    [
//    {
//        "transactionId": 777,
//            "username": "prashanth",
//            "accountId": 12345678789,
//            "amount": 3728832,
//            "transactionType": "deposit"
//    },
//    {
//        "transactionId": 77,
//            "username": "vignesh",
//            "accountId": 123678789,
//            "amount": 310000,
//            "transactionType": "withdraw"
//    }
//   ]
    @GetMapping("/findByRange/{rangeAmount1}/{rangeAmount2}")
    public List<TransactionEntity> callApiRangeAmount(@PathVariable("rangeAmount1") Long rangeAmount1, @PathVariable("rangeAmount2") Long rangeAmount2) {
        return transactionService.callFindRangeAmount(rangeAmount1, rangeAmount2);
    }
}
