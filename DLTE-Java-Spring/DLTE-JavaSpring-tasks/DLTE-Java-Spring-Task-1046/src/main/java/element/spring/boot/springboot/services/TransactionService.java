package element.spring.boot.springboot.services;

import element.spring.boot.springboot.model.TransactionEntity;
import element.spring.boot.springboot.remotes.TransactionJpaRepository;
import element.spring.boot.springboot.remotes.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionJpaRepository transactionJpaRepository;

    public TransactionEntity callSave(TransactionEntity transactionEntity){
        return transactionRepository.save(transactionEntity);
    }

    public List<TransactionEntity> callFindUserAndType(String username,String type){
        return transactionJpaRepository.lookByUsernameAndTransactionType(username,type);
    }

    public List<TransactionEntity> callFindRangeAmount(Long range1,Long range2){
        return transactionJpaRepository.lookByRangeAmount(range1,range2);
    }
}
