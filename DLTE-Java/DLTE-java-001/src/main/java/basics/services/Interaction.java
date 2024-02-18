package basics.services;
import java.util.*;
//command line interaction : Car loan
/*
Personal details : name,aadhar,pan,address,mobile,email
Income : salaried,self-employed :ITR
 */

public class Interaction {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="";
        Long mobileNumber=0L,aadhar=0L;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----WELCOME TO MY BANK----");
        System.out.println("Fill your Name");
        borrowerName = scanner.nextLine();
        System.out.println("Fill your aadhar number");
        aadhar = scanner.nextLong();
        System.out.println("Enter the pan");
        borrowerPan = scanner.next();
        System.out.println("Let us Know Income type(Salaried/Self Employed");
        borrowerIncomeType = scanner.next();
        System.out.println("Mention the mobile Number");
        mobileNumber = scanner.nextLong();
        System.out.println("Enter the email address");
        borrowerEmail = scanner.next();
        System.out.println("Dear " + borrowerName + " Thanks for showing interest in the loan in my bank your application is submitted  and further details will be mailed to you " + borrowerEmail + " mobile number " + mobileNumber);
    }
}
