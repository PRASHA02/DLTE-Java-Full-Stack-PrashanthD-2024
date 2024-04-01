package element.spring.boot.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/controller")
public class ControllerMapping {
    public ArrayList<Integer> arrayList = (ArrayList<Integer>) Stream.of(new Integer(1),new Integer(2)).collect(Collectors.toList());
    private List<Loan> loans = Stream.of(
            new Loan(117,50000.0,"19/03/2024","personalLoan","prashanth",789267517L,arrayList)
//            new Loan(111,60000.0,"20/02/2024","homeLoan","vikhyath",9836774457L,ar),
//            new Loan(120,400000.0,"30/05/2024","personalLoan","vignesh",908765432L),
//            new Loan(118,100000.0,"31/01/2024","homeLoan","rakesh",9087654364L)
    ).collect(Collectors.toList());


    @GetMapping("/{index}")
    public Loan displayLoan(@PathVariable int index){
        return loans.get(index);
    }

    @PostMapping("/")
    public String addLoan(@RequestBody Loan object){
        loans.add(object);
        return object +" has added";
    }
}
