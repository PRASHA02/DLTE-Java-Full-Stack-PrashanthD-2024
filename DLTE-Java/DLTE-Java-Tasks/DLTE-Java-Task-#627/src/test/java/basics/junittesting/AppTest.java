package basics.junittesting;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Unit test for MainClass.
 */
public class AppTest {

    static ArrayList<LoanProduct> arrayListLoanProduct = new ArrayList<>();

    @BeforeClass
    public static void initialize(){
        LoanProduct[] loan = {
                new LoanProduct(117,50000.0,new Date(2024,11,11),"closed","prashanth",789267517L),
                new LoanProduct(111,60000.0,new Date(2025,10,9),"opened","vikhyath",9836774457L),
                new LoanProduct(120,400000.0,new Date(2023,12,30),"closed","vignesh",908765432L),
        };
        arrayListLoanProduct.addAll(Stream.of(loan).collect(Collectors.toList()));
    }

    @Test
    public void testArrayListSize(){
        assertNotNull(arrayListLoanProduct);
        assertEquals(3, arrayListLoanProduct.size());
    }

    @Test
    public void testAddNewLoan(){
        // Prepare test data
        LoanProduct newLoan = arrayListLoanProduct.get(0);
        assertEquals(117L,newLoan.getLoanNumber().longValue());
        assertEquals(50000.0,newLoan.getLoanAmount(),0.1);
        assertEquals(new Date(2024,11,11),newLoan.getLoanDate());
        assertEquals("closed",newLoan.getLoanStatus());
        assertEquals("prashanth",newLoan.getBorrowerName());
        assertEquals(789267517L,newLoan.getBorrowerContact().longValue());

    }

    @Test
    public void testAvailableLoans(){
        // Invoke the method to be tested
        List<LoanProduct> loanProducts = arrayListLoanProduct.stream().filter(each-> each.getLoanStatus().equalsIgnoreCase("opened")).collect(Collectors.toList());
        assertTrue(loanProducts.stream().allMatch(loan -> loan.getLoanStatus().equalsIgnoreCase("opened"))); // Assuming only one loan is open in the initialized data
    }

    @Test
    public void testClosedLoans() {
        // Invoke the method to be tested
        List<LoanProduct> loanProducts = arrayListLoanProduct.stream().filter(each -> each.getLoanStatus().equalsIgnoreCase("closed")).collect(Collectors.toList());
        assertTrue(loanProducts.stream().allMatch(loan -> loan.getLoanStatus().equalsIgnoreCase("closed")));
    }

}
