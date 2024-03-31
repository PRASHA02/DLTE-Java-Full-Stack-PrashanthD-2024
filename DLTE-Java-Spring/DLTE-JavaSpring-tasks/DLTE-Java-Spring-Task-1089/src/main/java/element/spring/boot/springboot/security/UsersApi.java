package element.spring.boot.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//output
//{
//        "username": "prash",
//        "password": "$2a$10$mgZjfVTa4Jgqq0CKemKWru5Ch.gQqbnR5Jf/W4TswNGYo47Zmwime",
//        "role": "admin",
//        "address": "karkala",
//        "contact": 1234567890,
//        "email": "eprash@gmail.com",
//        "enabled": true,
//        "authorities": [
//        {
//        "authority": "admin"
//        }
//        ],
//        "accountNonLocked": true,
//        "accountNonExpired": true,
//        "credentialsNonExpired": true
//        }


//url:-localhost:8082/profile/register
@RestController
@RequestMapping("/profile")
public class UsersApi {
    @Autowired
    UsersServices myBankUserServices;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Users save(@RequestBody Users myBankUsers){
        myBankUsers.setPassword(passwordEncoder.encode(myBankUsers.getPassword()));
        return myBankUserServices.signingUp(myBankUsers);
    }
}

