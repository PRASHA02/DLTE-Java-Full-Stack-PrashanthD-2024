package oops.exceptions;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Gpay extends Accounts {
    static Scanner scanner = new Scanner(System.in);
    private Integer upiPin;
    private String userName;
    private Accounts account;

    public Gpay() {
    }

    public Gpay(Long accountNumber, Double accountBalance, String accountHolder, Integer upiPin, String userName) {
        super(accountNumber, accountBalance, accountHolder);
        this.upiPin = upiPin;
        this.userName = userName;
    }

    public Integer getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(Integer upiPin) {
        this.upiPin = upiPin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    static Gpay gPay = new Gpay(123456789L,300000.0,"prashanth",1234, "prash02");

    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) throws IOException {

         System.out.println("Welcome to Mobile Banking");
         Gpay gpay = new Gpay();
         int attempt=0;
        System.out.println("Enter the mPin");
        Integer mPin = scanner.nextInt();
         while(true && attempt<=4){
             try{
                 if(validPin(mPin)){
                     try{
                         payBill();
                     }catch(MyBankException e){
                         logger.log(Level.SEVERE,resourceBundle.getString("insufficient.funds"));
                         System.out.println("Insufficient balance");
                     }
                     return;
                 }
             }catch(MyBankException e){
                 attempt++;
                 if(attempt<5){
                     logger.log(Level.WARNING,resourceBundle.getString("exception.retry"));
                     System.out.println("Please Re-Enter the mPin.You have just "+(5-attempt)+" chances");
                     mPin = scanner.nextInt();
                     continue;
                 }
                 if(attempt>=5){
                     logger.log(Level.SEVERE,resourceBundle.getString("max.try"));
                     throw new MyBankException("Account Blocked");
                 }
             }
        }



    }

    public static void payBill(){
        scanner.nextLine();
        System.out.println("Enter the Biller Name");
        String billedName = scanner.nextLine();
        System.out.println("Enter the Biller Amount");
        Double billedAmount = scanner.nextDouble();
        System.out.println("Enter the Biller Type");
        String billedType = scanner.next();

            if(gPay.getAccountBalance()-billedAmount>=0){
                gPay.setAccountBalance(gPay.getAccountBalance()-billedAmount);
                logger.log(Level.INFO,resourceBundle.getString("payment.successfull"));
                System.out.println("Payment successfully done By "+billedName+" of amount "+billedAmount+" and your current account balance is "+gPay.getAccountBalance());
            }else{
                throw new MyBankException();
            }
        }
    static Boolean validPin(Integer mPin){
        if(gPay.getUpiPin().equals(mPin)){
            logger.log(Level.INFO,resourceBundle.getString("mpin.successful"));
            return true;
        }else{
            throw new MyBankException();
        }
    }
}
