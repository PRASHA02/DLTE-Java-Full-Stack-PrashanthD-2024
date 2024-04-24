package spring.element.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBankService {

    @Autowired
    @Qualifier("HomeLoan")
    private AutoWireInterface autoWireInterface;

    public List<Loan> executeFindAll() {
        return autoWireInterface.find();
    }

}
