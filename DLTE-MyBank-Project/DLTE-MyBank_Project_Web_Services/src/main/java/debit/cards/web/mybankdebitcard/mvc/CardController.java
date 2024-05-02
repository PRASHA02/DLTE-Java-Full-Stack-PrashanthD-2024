package debit.cards.web.mybankdebitcard.mvc;

import debit.cards.dao.entities.DebitCard;
import debit.cards.dao.remotes.DebitCardRepository;
import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import debit.cards.dao.services.DebitCardServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    DebitCardRepository debitCardRepository;

    @Autowired
    CardSecurityServices cardSecurityServices;

    Logger logger= LoggerFactory.getLogger(CardController.class);

    ResourceBundle bundle=ResourceBundle.getBundle("card");

    @GetMapping("/login")
    public String index(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @RequestMapping(value = "/account",method = RequestMethod.GET)
    public String accountView() {
        return "account";
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String debitCardDetails(){
        return "view";
    }

//    @RequestMapping(value = "/update",method = RequestMethod.GET)
//    public String updateCardLimit(){
//        return "update";
//    }

    @GetMapping("/update")
    public String updateCardLimit(@RequestParam Long accountNumber,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<DebitCard> debitCard = debitCardRepository.getDebitCard(username);
        List<DebitCard> filteredCardDetails = debitCard.stream()
                .filter(accountNum -> accountNum.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());
        model.addAttribute("sendCardDetails",filteredCardDetails.get(0));
        return "update";
    }


    @GetMapping("/name")
    @ResponseBody
    public String Customername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        CardSecurity cardSecurity = cardSecurityServices.findByUserName(name);
        return cardSecurity.getCustomerName();
    }





}
