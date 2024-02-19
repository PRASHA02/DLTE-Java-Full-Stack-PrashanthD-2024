package basics.services;

import java.util.Scanner;

public class MobileBanking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fullName,lastName,email,branchName,ifscCode,userName,password;
        int mpin;
        long phoneNumber,aadhar,accountNumber;
        System.out.println("-----Welcoome to Mobile Banking-----");
        System.out.println("Enter the MPIN");
        mpin = scanner.nextInt();
        System.out.println("Congratulations Successfully logged in!");
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
        System.out.println("Enter the user name");
        userName = scanner.next();
        System.out.println("Enter the password");
        password = scanner.next();
        System.out.println("Thank you "+fullName + " "+lastName+" for using the Mobile banking your account Number is "+accountNumber+". Your username is "+userName+" and otp sent to Your Mobile Number "+phoneNumber+".");
    }
}
