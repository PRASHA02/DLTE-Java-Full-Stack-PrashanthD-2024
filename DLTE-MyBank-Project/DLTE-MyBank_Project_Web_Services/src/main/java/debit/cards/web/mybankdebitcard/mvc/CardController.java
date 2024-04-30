package debit.cards.web.mybankdebitcard.mvc;

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
import java.util.ResourceBundle;

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

    @GetMapping("/name")
    @ResponseBody
    public String Customername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        CardSecurity cardSecurity = cardSecurityServices.findByUserName(name);
        return cardSecurity.getUsername();
    }

    @GetMapping("/account")
    public String accountView() {
        return "account";
    }

}
