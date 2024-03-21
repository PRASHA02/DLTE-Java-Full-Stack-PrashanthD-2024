package element.spring.boot.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    //inserting the data
    //url-http://localhost:8082/transaction/
    //output-{
    //  "transactionId": 118,
    //  "transactionDate": "2024-03-30",
    //  "transactionBy": "prashanth",
    //  "transactionTo": "vineeth",
    //  "transactionAmount": 100000,
    //  "transactionFor": "mobile"
    //}
    @PostMapping("/")
    public TransactionsModel callSave(@RequestBody TransactionsModel transactionsModel){
        TransactionsModel transactionsModel1 = null;
        try{
            transactionsModel1 =  transactionServices.apiSave(transactionsModel);
        }catch (TransactionException transactionException){
            logger.warn(transactionException.toString());
        }

        return transactionsModel1;
    }

    //finding the data by transaction_by
    //url - http://localhost:8082/transaction/findBy/prashanth

    //output-[
    //    {
    //        "transactionId": 123,
    //        "transactionDate": "2024-03-21",
    //        "transactionBy": "prashanth",
    //        "transactionTo": "vignesh",
    //        "transactionAmount": 10000,
    //        "transactionFor": "Payment"
    //    },
    //    {
    //        "transactionId": 120,
    //        "transactionDate": "2024-03-21",
    //        "transactionBy": "prashanth",
    //        "transactionTo": "vignesh",
    //        "transactionAmount": 10000,
    //        "transactionFor": "Payment"
    //    },
    //    {
    //        "transactionId": 118,
    //        "transactionDate": "2024-03-30",
    //        "transactionBy": "prashanth",
    //        "transactionTo": "vineeth",
    //        "transactionAmount": 100000,
    //        "transactionFor": "mobile"
    //    }
    //]

    @GetMapping("/findBy/{by}")
    public List<TransactionsModel> callFindBy(@PathVariable("by") String by){
        return transactionServices.apiFindBySender(by);
    }

    //finding the data by transaction_to
    //url - http://localhost:8082/transaction/findTo/vineeth
    //output-[
    //    {
    //        "transactionId": 118,
    //        "transactionDate": "2024-03-30",
    //        "transactionBy": "prashanth",
    //        "transactionTo": "vineeth",
    //        "transactionAmount": 100000,
    //        "transactionFor": "mobile"
    //    }
    //]

    @GetMapping("/findTo/{to}")
    public List<TransactionsModel> callFindTo(@PathVariable("to") String to){
        return transactionServices.apiFindByReceiver(to);
    }

    //finding the data by transaction_amount
    //url - http://localhost:8082/transaction/findAmount/1000000

    //-output-[
    //    {
    //        "transactionId": 118,
    //        "transactionDate": "2024-03-30",
    //        "transactionBy": "prashanth",
    //        "transactionTo": "vineeth",
    //        "transactionAmount": 100000,
    //        "transactionFor": "mobile"
    //    }
    //]

    @GetMapping("/findAmount/{amount}")
    public List<TransactionsModel> callFindBy(@PathVariable("amount") Long amount){
        return transactionServices.apiFindByAmount(amount);
    }
}
