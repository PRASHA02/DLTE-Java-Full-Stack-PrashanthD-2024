package oops.concept;

import java.util.Scanner;

public class Gpay extends Account {

    private Integer upiPin;
    private String username;

    public Gpay(Long accountNumber, Double accountBalance, String accountHolder,Integer upiPin,String username) {
        super(accountNumber, accountBalance, accountHolder);
        this.upiPin = upiPin;
        this.username = username;
    }

    public Integer getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(Integer upiPin) {
        this.upiPin = upiPin;
    }

    public static void main(String[] args) {
        Gpay gpay = new Gpay(123456789123L,200000.0,"prashanth",2002,"PRASH321");
        gpay.payBills(gpay);

    }

    public void payBills(Gpay gpay){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the bill name ");
        String billerName = scanner.next();
        System.out.println("Enter the bill amount ");
        Double billerAmount = scanner.nextDouble();
        System.out.println("Enter the bill Type ");
        String billerType = scanner.next();
        System.out.println("Enter the pin number");
        String upiPin = scanner.next();
        if(gpay.getAccountBalance().compareTo(billerAmount)>0){
            if(gpay.getUpiPin().equals(upiPin)){
                System.out.println(gpay.toString());
            }
            else{
                System.out.println("Upi pin is Invalid");
            }
        }else{
            System.out.println("Invalid biller amount! Insufficient fund");
        }

    }

}

