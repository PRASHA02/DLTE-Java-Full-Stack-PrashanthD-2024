package debit.cards.web.mybankdebitcard.mvc;

import debit.cards.dao.remotes.DebitCardRepository;

import debit.cards.dao.security.Customer;
import debit.cards.dao.security.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/card")
public class MvcController {
    @Autowired
    DebitCardRepository debitCardRepository;

    @Autowired
    CustomerServices customerServices;

    Logger logger= LoggerFactory.getLogger(MvcController.class);

    ResourceBundle bundle=ResourceBundle.getBundle("webservice");


    @GetMapping("/login")
    public String index(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String debitCardDetails(){
        return "view";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String updateCardLimit(){
        return "update";
    }

//    @GetMapping("/update")
//    public String updateCardLimit(@RequestParam Long accountNumber,Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        List<DebitCard> debitCard = debitCardRepository.getDebitCard(username);
//        List<DebitCard> filteredCardDetails = debitCard.stream()
//                .filter(accountNum -> accountNum.getAccountNumber().equals(accountNumber))
//                .collect(Collectors.toList());
//        model.addAttribute("sendCardDetails",filteredCardDetails.get(0));
//        return "update";
//    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/name")
    @ResponseBody
    public String Customername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Customer customer = customerServices.findByUserName(name);
        return customer.getCustomerName();
    }





}
