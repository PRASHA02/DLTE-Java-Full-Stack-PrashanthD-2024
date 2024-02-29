package oops.exceptions;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gpay extends Accounts {
    static Scanner scanner = new Scanner(System.in);
    private Accounts account;
    private Integer upiPin;
    private String userName;
    static Accounts account1 = new Accounts(123456789L,300000.0,"prashanth");
    static Accounts account2 = new Accounts(324536359L,80000.0,"vignesh");
    static Accounts account3 = new Accounts(218732565L,400000.0,"vineeth");
    static Accounts account4 = new Accounts(327632521L,50000.0,"elroy");
    Accounts[] accounts ={account1,account2,account3,account4};
    public Gpay(Accounts account, Integer upiPin, String userName) {
        super();
        this.account = account;
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


    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    static Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        Gpay gpay1 = new Gpay(account1,1234,"prash02");
        Gpay gpay2 = new Gpay(account2,1234,"vignesh02");
        Gpay gpay3 = new Gpay(account3,1234,"vineeth05");
        Gpay gpay4 = new Gpay(account4,1234,"elroy02");
        Gpay[] gpay = {gpay1,gpay2,gpay3,gpay4};
//        pay(gpay);
        for(Gpay each:gpay){
            System.out.println(each);
        }
    }

//    public static void pay(Gpay[] gpay){
//        System.out.println("Welcome to Mobile Banking");
//        System.out.println("Enter the Biller Name");
//        String billerName = scanner.nextLine();
//        System.out.println("Enter the Biller Amount");
//        Double billerAmount = scanner.nextDouble();
//        System.out.println("Enter the Biller Type");
//        String billerType = scanner.next();
//        logger.log(Level.INFO,resourceBundle.getString("members.init"));
//        int attempt =0;
//
//        while(attempt<5){
//            try{
//                System.out.println("Enter the UPI pin");
//                Integer upiPin = scanner.nextInt();
//                for(Gpay each:gpay){
//                    if(each.getUpiPin().equals(upiPin)){
//                        displayBills(billerName,billerAmount,billerType);
//                        break;
//                    }
//                }
//            }catch(MyBankException myBankException){
//                attempt+=1;
//                logger.log(Level.WARNING,resourceBundle.getString("exception.retry"));
//                if (attempt >= 5) {
//                    logger.log(Level.SEVERE,resourceBundle.getString("max.try"));
//                    break;
//                }
//            }
//        }
//
//    }
//
//    private static void displayBills() {
//        if(billerAmount)
//    }


}
