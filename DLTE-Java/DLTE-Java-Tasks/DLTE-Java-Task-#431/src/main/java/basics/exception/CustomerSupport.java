package basics.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerSupport {
    static Scanner scanner = new Scanner(System.in);

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        logger.setLevel(Level.ALL); // Setting logger level
        try {
            CreditCard[] creditCard = {
                    new CreditCard(8765678765678L,"Prashanth D",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                    new CreditCard(2343234345445L,"Vignesh",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
                    new CreditCard(8765678764545L,"Sanath",new Date(2031,5,15),955,200000,new Date(2024,3,10),new Date(2024,03,11),9864),
                    new CreditCard(1234565456767L,"Akash",new Date(2028,8,11),767,10000,new Date(2024,3,18),new Date(2024,03,29),1945),

            };
            CustomerSupport customerSupport = new CustomerSupport();
            System.out.println("----Welcome to my Bank----");
            System.out.println("1 -> Filter the Limit\n2 -> Filter the date of bill Payment");
            System.out.println("Enter Your Choice");
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    customerSupport.creditCardLimit(creditCard);
                    break;
                case 2:
                    customerSupport.creditCardBillPayment(creditCard,new Date(2023,03,11),new Date(2024,03,30));
                    break;
            }
        } catch (MyBankCreditCardException e) {
            logger.log(Level.WARNING, resourceBundle.getString("limit.exception"));
            throw new MyBankCreditCardException("Limit exceeded");
        }
    }

    public void creditCardLimit(CreditCard[] creditCardLimit){
        CreditCard temp = null;
        try {
            for (int select = 0; select < creditCardLimit.length - 1; select++) {
                for (int next = select + 1; next < creditCardLimit.length; next++) {
                    if (creditCardLimit[select].getCreditCardLimit().compareTo(creditCardLimit[next].getCreditCardLimit()) < 0) {
                        temp = creditCardLimit[select];
                        creditCardLimit[select] = creditCardLimit[next];
                        creditCardLimit[next] = temp;
                    }

                }
            }
            for (CreditCard each : creditCardLimit) {
                System.out.println("The Card limit is " + each);
            }
        } catch(MyBankCreditCardException e){
            logger.log(Level.WARNING, resourceBundle.getString("limit.exception"));
            throw new MyBankCreditCardException(resourceBundle.getString("limit.exception"));
        }
    }

    public void creditCardBillPayment(CreditCard[] creditCard,Date start,Date end){
        try{
            for(CreditCard each:creditCard){
                if (each.getDateOfBillGeneration().getDate() >= start.getDate() && each.getDateOfBillGeneration().getDate() <= end.getDate()) {
                    System.out.println(each.toString());
                }
            }
        } catch(MyBankCreditCardException e){
            logger.log(Level.WARNING, resourceBundle.getString("bill.exception"));
            throw new MyBankCreditCardException(resourceBundle.getString("bill.exception"));
        }
    }
}
