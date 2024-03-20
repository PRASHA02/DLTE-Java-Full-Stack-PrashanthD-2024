package spring.element.autowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("personalLoan")
public class PersonalLoanImplementation implements AutoWireInterface {
    @Override
    public List<Loan> find() {
        List<Loan> personalLoan = new ArrayList<>();

        for(Loan each:loans){
            if(each.getLoanType().equalsIgnoreCase("personalLoan")){
                personalLoan.add(each);
            }
        }
        return personalLoan;

    }
}
