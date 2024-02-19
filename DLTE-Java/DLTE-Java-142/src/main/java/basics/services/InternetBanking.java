package basics.services;

import java.util.Scanner;

public class InternetBanking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fullName,lastName,email,branchName,ifscCode;
        long phoneNumber,aadhar,accountNumber;
        System.out.println("-----Welcoome to Internet Banking-----");
        System.out.println("Enter the Full Name");
        fullName = scanner.next();
        System.out.println("Enter the last Name");
        lastName = scanner.next();
        System.out.println("Enter the aadhar");
       aadhar = scanner.nextLong();
        System.out.println("Enter the phone Number");
        phoneNumber = scanner.nextLong();
        System.out.println("Enter the Email");
        email = scanner.next();
        System.out.println("Enter the account Number");
        accountNumber = scanner.nextLong();
        System.out.println("Enter the branch Name");
        branchName = scanner.next();
        System.out.println("Enter the IFSC Code");
        ifscCode = scanner.next();
        System.out.println("Thank you "+fullName + " "+lastName+" for using the Net banking your account Number is "+accountNumber+" in your Branch "+branchName+" and details are updated to your mobile Number "+phoneNumber+".");
    }
}