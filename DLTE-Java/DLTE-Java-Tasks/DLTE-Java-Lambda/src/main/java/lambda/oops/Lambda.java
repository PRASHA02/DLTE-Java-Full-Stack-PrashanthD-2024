package lambda.oops;

import java.util.Date;

public class Lambda{

    static Loan[] loan = {
            new Loan(117,50000.0,new Date(2024,11,11),"closed","prashanth",789267517L),
            new Loan(111,60000.0,new Date(2025,10,9),"opened","vikhyath",9836774457L),
            new Loan(120,400000.0,new Date(2023,12,30),"closed","vignesh",908765432L),

    };
    static Date start = new Date(2022,02,9);
    static Date stop = new Date(2024,12,11);
    public static void main(String[] args) {
            MyBank myBank = (datestart,dateend )-> {
                for(Loan each:loan){
                    if(each.getLoanDate().after(start) && each.getLoanDate().before(stop)){
                        System.out.println(each.toString());
                    }
                }
            };

            myBank.filterDates(start,stop);
    }

}
