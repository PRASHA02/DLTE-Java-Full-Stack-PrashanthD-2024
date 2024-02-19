package basics.services;
import java.util.*;
import  java.math.*;

import static java.lang.Math.pow;

public class personalLoan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String borrowerName,borrowerPan,borrowerAddress,borrowerEmail,borrowerIncomeType;
        long mobileNumber,aadhar,loanAmount,monthlyIncome;
        int loanTenure;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----WELCOME TO MY BANK----");
        System.out.println("Fill your Name");
        borrowerName = scanner.nextLine();
        System.out.println("Fill your address");
        borrowerAddress= scanner.nextLine();
        System.out.println("Fill your aadhar number");
        aadhar = scanner.nextLong();
        System.out.println("Enter the pan");
        borrowerPan = scanner.next();
        System.out.println("Mention the mobile Number");
        mobileNumber = scanner.nextLong();
        System.out.println("Enter the email address");
        borrowerEmail = scanner.next();
        System.out.println("Fill your IncomeType");
        borrowerIncomeType= scanner.next();
        System.out.println("Fill your MonthlyIncome");
        monthlyIncome= scanner.nextLong();
        System.out.println("Fill the Loan Amount");
        loanAmount= scanner.nextLong();
        System.out.println("Fill your LoanTenure");
        loanTenure= scanner.nextInt();
        System.out.println("Dear " + borrowerName + " Thanks for showing interest in the personal loan in my bank your application is submitted  and further details will be mailed to you " + borrowerEmail + "\nmobile number " + mobileNumber + "\nYour Income type  is "+ borrowerIncomeType + "\nMonthly Income is "+ monthlyIncome + "\nLoan Amount is "+loanAmount+"\nLoan tenure is "+ loanTenure);
    }
}
