package element.spring.boot.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public TransactionsModel callSave(@RequestBody TransactionsModel transactionsModel) {
        TransactionsModel transactionsModel1 = null;
        try {
            transactionsModel1 = transactionServices.apiSave(transactionsModel);
        } catch (TransactionException transactionException) {
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
    public List<TransactionsModel> callFindBy(@PathVariable("by") String by) {
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
    public List<TransactionsModel> callFindTo(@PathVariable("to") String to) {
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
    public List<TransactionsModel> callFindBy(@PathVariable("amount") Long amount) {
        return transactionServices.apiFindByAmount(amount);
    }

    //updating the data
    //url-http://localhost:8082/transaction/updateRemarks
    //output-{
    //  "transactionId": 118,
    //  "transactionDate": "2024-03-30",
    //  "transactionBy": "prashanth",
    //  "transactionTo": "vineeth",
    //  "transactionAmount": 100000,
    //  "transactionFor": "friend"
    //}

    @PutMapping("/updateRemarks")
    public TransactionsModel callUpdateTransaction(@RequestBody TransactionsModel transactionsModel) {
        TransactionsModel transaction1 = transactionServices.apiUpdate(transactionsModel);
        return transaction1;
    }

    //delete the date
    //url-http://localhost:8082/transaction/deleteByDate/2024-03-21/2024-03-30
    //output-{
    //removed transaction between 2024-03-21 and 2024-03-30
    //}

    @DeleteMapping("/deleteByDate/{start}/{end}")
    public String callDeleteTransaction(@PathVariable("start") String start, @PathVariable("end") String end) throws ParseException {
        SimpleDateFormat dateFormatStart = new SimpleDateFormat(start);
        Date dateStart = dateFormatStart.parse(start);
        SimpleDateFormat dateFormatEnd = new SimpleDateFormat(end);
        Date dateEnd = dateFormatStart.parse(end);
        String result = transactionServices.removeByDate(dateStart, dateEnd);
        if (result.contains("removed"))
            return result + " transaction between " + start + " to " + end;
        else
            return result + " not removed";

    }
}
