package element.spring.boot.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//url:-localhost:8082/profile/register
@RestController
@RequestMapping("/profile")
public class MyBankUsersApi {
    @Autowired
    MyBankUserServices myBankUserServices;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankUsers save(@RequestBody MyBankUsers myBankUsers){
        myBankUsers.setPassword(passwordEncoder.encode(myBankUsers.getPassword()));
        return myBankUserServices.signingUp(myBankUsers);
    }
}
