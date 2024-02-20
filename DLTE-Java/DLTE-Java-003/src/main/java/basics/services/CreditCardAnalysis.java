package basics.services;

import java.util.Date;
import java.util.Scanner;

public class CreditCardAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //giving new values for a single object using constructor
        CreditCard[] Bank={
                new CreditCard(8765678765678L,"Prashanth D",new Date(2030,12,30),333,200000,new Date(2024,3,11),new Date(2024,03,11),8888),
                new CreditCard(2343234345445L,"Elroy",new Date(2029,1,4),134,60000,new Date(2024,3,2),new Date(2024,03,22),9559),
                new CreditCard(8765678764545L,"Vignesh",new Date(2031,5,15),455,80000,new Date(2024,3,10),new Date(2024,03,11),9764),
                new CreditCard(1234565456767L,"Akash",new Date(2032,8,11),767,800000,new Date(2024,3,18),new Date(2024,03,29),1645),
        };

        //creating an instance for a class
        CreditCardAnalysis analysis=new CreditCardAnalysis();

        int limit,day,mpin,date;

        //limit customers
        System.out.println("Enter the limit");
        limit = scanner.nextInt();
        analysis.limitCustomers(Bank,limit);

        //billpayment
        System.out.println("Enter the day");
        day = scanner.nextInt();
        analysis.billPayment(Bank,day);

        //Update mpin
        long creditCardNo;
        System.out.println("Enter the Account number");
        creditCardNo = scanner.nextLong();
        System.out.println("Enter the  mpin number");
        mpin = scanner.nextInt();
        analysis.updatePin(Bank,creditCardNo,mpin);
        //update limit
        System.out.println("Enter the date to updated in Customer bill generation");
        date = scanner.nextInt();
        analysis.limitUpdate(Bank,date);
    }

    public void limitCustomers(CreditCard[] customers,int limit){
        for(CreditCard each:customers){
                if (each.getCreditCardLimit() <= limit) {
                    System.out.println(each.getCreditCardHolder() + " who has limit lesser than or equal to " + limit);
                }
        }
    }

    public void billPayment(CreditCard[] customers,int day){

        for(CreditCard each:customers){
            if(each.getDateOfBillPayment().getDate()<=day){
                System.out.println(each.getCreditCardHolder()+" who having bill day less than "+day);
            }
        }
    }
    public void updatePin(CreditCard[] customers,long creditNo,int mpin){
        for(CreditCard each:customers){
            if(creditNo==each.getCreditCardNumber()){
                if(mpin==each.getCreditCardPin()){
                    int updatePin;
                    System.out.println("Enter the update pin");
                    updatePin = new Scanner(System.in).nextInt();
                     each.setCreditCardPin(updatePin);
                    System.out.println("Congratulations Pin updated successfully ");
                }
            }
        }
    }

    public void limitUpdate(CreditCard[] customers,int day){
        for(CreditCard each:customers){
            if(each.getDateOfBillPayment().getDate()==day){
                int limit;
                System.out.println("Enter the limit to be updated");
                limit = new Scanner(System.in).nextInt();
                each.setCreditCardLimit(limit);
                System.out.println("Congratulations limit updated successfully is "+limit);
            }
        }
    }

}
