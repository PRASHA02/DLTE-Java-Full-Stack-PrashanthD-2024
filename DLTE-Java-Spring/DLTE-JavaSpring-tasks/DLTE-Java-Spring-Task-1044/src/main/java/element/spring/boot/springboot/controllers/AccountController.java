package element.spring.boot.springboot.controllers;

import element.spring.boot.springboot.models.AccountInfo;
import element.spring.boot.springboot.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restApi")
public class AccountController {
    @Autowired
    AccountServices accountServices;
    @PostMapping("/open")
    public AccountInfo save(@RequestBody AccountInfo accountInfo){
        return  accountServices.callSave(accountInfo);
    }
    @PutMapping(value = "/",consumes = "application/json")
    public AccountInfo update(AccountInfo accountInfo){
        return  accountServices.callSave(accountInfo);
    }

    @GetMapping("/findAll")
    public List<AccountInfo> findAll(){
        return accountServices.callFindAll();
    }

}
