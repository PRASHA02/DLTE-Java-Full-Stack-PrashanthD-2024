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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/web")
public class MvcController {
    @Autowired
    TransactionServices transactionServices;

    Logger logger = LoggerFactory.getLogger(MvcController.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");


    @GetMapping("/")
    public String securityLogin(){
        return "security";
    }

    @GetMapping("/newtransaction")
    public String requestToSubmission(Model model){
        TransactionsModel transactionsModel = new TransactionsModel();
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "newtransaction.html";
    }

    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    public String newTransaction(@Valid TransactionsModel transactionsModel, BindingResult bindingResult, Model model){
        try{
            if(!bindingResult.hasErrors()){
                transactionsModel = transactionServices.apiSave(transactionsModel);
                model.addAttribute("status",transactionsModel.getTransactionId()+" has inserted");
                logger.info(resourceBundle.getString("insert.success"));
            }
        }catch(TransactionException transactionException){
            logger.warn(transactionException.toString());
            model.addAttribute("error",transactionException.toString());
            return "newtransaction";
        }
        return "newtransaction.html";
    }
    @GetMapping("/sname")
    public String searchShow(Model model){
        TransactionsModel transactionsModel=new TransactionsModel();
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "sender";
    }
    @RequestMapping(value="/sender",method = RequestMethod.GET)
    public String findBySender(@RequestParam("sname") String sname, Model model){
        List<TransactionsModel> transactionsModels = transactionServices.apiFindBySender(sname);
        model.addAttribute("transactionsModels",transactionsModels);
        return "sender";
    }
    @GetMapping("/rname")
    public String searchRshow(Model model){
        TransactionsModel transactionsModel=new TransactionsModel();
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "receiver";
    }

    @RequestMapping(value="/receiver",method = RequestMethod.GET)
    public String findByReceiver(@RequestParam("rname") String rname, Model model){
        List<TransactionsModel> transactionsModels = transactionServices.apiFindByReceiver(rname);
        model.addAttribute("transactionsModels",transactionsModels);
        return "receiver";
    }

    @GetMapping("/price")
    public String searchAmountshow(Model model){
        TransactionsModel transactionsModel=new TransactionsModel();
        model.addAttribute("transactionsModel",new TransactionsModel());
        return "amount";
    }

    @RequestMapping(value="/amount",method = RequestMethod.GET)
    public String findByAmount(@RequestParam("price") Long price, Model model){
        List<TransactionsModel> transactionsModels = transactionServices.apiFindByAmount(price);
        model.addAttribute("transactionsModels",transactionsModels);
        return "amount";
    }


    @GetMapping("/date")
    public String deleteShow(Model model){
        model.addAttribute("transactionsModels",new TransactionsModel());
        return "removedate";
    }


    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String blockCard(@RequestParam("start") String start, @RequestParam("end") String end , Model model){
        try{
            SimpleDateFormat dateFormatStart = new SimpleDateFormat(start);
            Date dateStart = dateFormatStart.parse(start);
            SimpleDateFormat dateFormatEnd = new SimpleDateFormat(end);
            Date dateEnd = dateFormatEnd.parse(end);
            String result = transactionServices.removeByDate(dateStart, dateEnd);
            if (result.contains("removed"))
                model.addAttribute("status",result + " transaction between " + start + " to " + end);
            else
                model.addAttribute("status", result + " not removed");
        }
        catch (TransactionException | ParseException cardException){
            model.addAttribute("error",cardException.toString());
            return "removedate";
        }
        return "removedate.html";

    }



}
