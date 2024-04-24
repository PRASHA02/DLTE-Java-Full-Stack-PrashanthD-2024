package spring.element.autowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("HomeLoan")
public class HomeLoanImplementation implements AutoWireInterface {
    @Override
    public List<Loan> find() {
        List<Loan> homeLoan = new ArrayList<>();

        for (Loan each : loans) {
            if (each.getLoanType().equalsIgnoreCase("homeLoan")) {
                homeLoan.add(each);
            }
        }
        return homeLoan;
    }
}
