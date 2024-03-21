package element.spring.boot.springboot.services;

import element.spring.boot.springboot.models.AccountInfo;
import element.spring.boot.springboot.remotes.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServices {
    @Autowired
    AccountRepository accountRepository;

    public AccountInfo callSave(AccountInfo accountInfo){
        return accountRepository.save(accountInfo);
    }
    public List<AccountInfo> callFindAll(){
        return (List<AccountInfo>) accountRepository.findAll();
    }
}
