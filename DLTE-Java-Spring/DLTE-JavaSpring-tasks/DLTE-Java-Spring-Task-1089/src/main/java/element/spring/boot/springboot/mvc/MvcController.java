package element.spring.boot.springboot.mvc;

import element.spring.boot.springboot.TransactionException;
import element.spring.boot.springboot.TransactionServices;
import element.spring.boot.springboot.TransactionsModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/web")
public class MvcController {
    @Autowired
    TransactionServices transactionServices;

    Logger logger = LoggerFactory.getLogger(MvcController.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @GetMapping("/newtransaction")
    public String requestToSubmission(Model model){
        TransactionsModel transactionsModel = new TransactionsModel();
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "newtransaction.html";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public String newTransaction(TransactionsModel transactionsModel,BindingResult bindingResult,Model model){
        try{
            if(!bindingResult.hasErrors()){
                transactionsModel = transactionServices.apiSave(transactionsModel);
                model.addAttribute("status",transactionsModel.getTransactionId()+" has inserted");
            }
        }catch(TransactionException transactionException){
            logger.warn(transactionException.toString());
            model.addAttribute("error",transactionException.toString());
        }
        return "newtransaction.html";
    }

}
