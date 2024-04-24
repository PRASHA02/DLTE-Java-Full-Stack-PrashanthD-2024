package loans.logic;



import com.sun.org.glassfish.gmbal.ManagedOperation;

import javax.faces.bean.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@ApplicationScoped
public class LoanServices {
    private List<Loans> loans = new ArrayList<>();

    public List<Loans> getLoans() {
        return loans;
    }

    public void setLoans(List<Loans> loans) {
        this.loans = loans;
    }

    @PostConstruct
    public void init() {
        Loans[] loansList = {
                new Loans(117L,50000.0,"11/11/2024","closed","prashanth",789267517L),
                new Loans(111L,60000.0,"09/10/2025","opened","vikhyath",9836774457L),
                new Loans(120L,400000.0,"30/12/2025","closed","vignesh",908765432L),
        };
        loans.addAll(Stream.of(loansList).collect(Collectors.toList()));
    }

    //adding the new Loan
    public void addLoan(Loans newLoan) {
        try{
            loans.add(newLoan);
            System.out.println("The new Loan is inserted Successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
   //display closed loans
    public List<Loans> closedLoans() {
        List<Loans> loanProducts = loans.stream().filter(each-> each.getLoanStatus().equalsIgnoreCase("closed")).collect(Collectors.toList());
        loanProducts.forEach(System.out::println);
        return loanProducts;
    }
    //delete the loans based on loan Number
    public void deleteLoans(long loanNumber){
        loans.removeIf(loans -> loans.getLoanNumber().equals(loanNumber));
        System.out.println("Loan Deleted Successfully");
    }

}
