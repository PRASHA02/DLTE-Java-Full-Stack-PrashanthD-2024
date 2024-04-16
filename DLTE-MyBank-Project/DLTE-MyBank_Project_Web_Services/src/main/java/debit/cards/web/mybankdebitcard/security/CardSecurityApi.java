package debit.cards.web.mybankdebitcard.security;

import debit.cards.dao.security.CardSecurity;
import debit.cards.dao.security.CardSecurityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class CardSecurityApi {

    @Autowired
    CardSecurityServices cardSecurityServices;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public CardSecurity save(@RequestBody CardSecurity cardSecurity){
        cardSecurity.setPassword(passwordEncoder.encode(cardSecurity.getPassword()));
        return cardSecurityServices.signingUp(cardSecurity);
    }
}
