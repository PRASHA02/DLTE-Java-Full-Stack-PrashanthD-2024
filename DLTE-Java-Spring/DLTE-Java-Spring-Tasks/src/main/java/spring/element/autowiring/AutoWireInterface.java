package spring.element.autowiring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public  interface AutoWireInterface {

     List<Loan> loans = Stream.of(
             new Loan(117,50000.0,new Date(2024,11,11),"personalLoan","prashanth",789267517L),
             new Loan(111,60000.0,new Date(2025,10,9),"homeLoan","vikhyath",9836774457L),
             new Loan(120,400000.0,new Date(2023,12,30),"personalLoan","vignesh",908765432L),
             new Loan(118,100000.0,new Date(2024,05,1),"homeLoan","rakesh",9087654364L)
     ).collect(Collectors.toList());


     List<Loan> find();
}
