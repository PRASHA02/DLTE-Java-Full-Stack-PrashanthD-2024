package basics.services;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarLoan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String borrowerName,borrowerPan,borrowerEmail,borrowerIncomeType,aadhar,mobileNumber;

        Scanner scanner = new Scanner(System.in);
        System.out.println("----WELCOME TO MY BANK----");
        System.out.println("Fill your Name");
        borrowerName = scanner.nextLine().toUpperCase();
        String name = "^[A-Z]+$";
        Pattern pattern = Pattern.compile(name);
        Matcher matcher = pattern.matcher(borrowerName);
        if(matcher.matches()){
            System.out.println("Its a Valid name: "+borrowerName);
        }else{
            System.out.println("Invalid name");
        }
        System.out.println("Fill your aadhar number");
        aadhar = scanner.next();
        String aadharNumber = "\\d{12}";
        pattern = Pattern.compile(aadharNumber);
        matcher = pattern.matcher(aadhar);
        if(matcher.matches()){
            System.out.println("Its a Valid aadhar Number: "+borrowerName);
        }else{
            System.out.println("Invalid aadhar Number");
        }
        System.out.println("Enter the pan");
        borrowerPan = scanner.next();
        String panNumber = "[A-Z]{5}[0-9]{4}[A-Z]";
        pattern = Pattern.compile(panNumber);
        matcher = pattern.matcher(borrowerPan);
        if(matcher.matches()){
            System.out.println("Its a Valid Pan Number: "+borrowerPan);
        }else{
            System.out.println("Invalid pan Number");
        }
        System.out.println("Mention the mobile Number");
        mobileNumber = scanner.next();
        String phone = "\\d{10}";
        pattern = Pattern.compile(phone);
        matcher = pattern.matcher(mobileNumber);
        if(matcher.matches()){
            System.out.println("Its a Valid phone Number: "+mobileNumber);
        }else{
            System.out.println("Invalid phone Number");
        }
        System.out.println("Enter the email address");
        borrowerEmail = scanner.next();
        String email = "^[A-Za-z0-9]{3,}@[A-Za-z]{3,6}\\.[a-z]{2,}$";
        pattern = Pattern.compile(email);
        matcher = pattern.matcher(borrowerEmail);
        if(matcher.matches()){
            System.out.println("Its a Valid Email: "+borrowerEmail);
        }else{
            System.out.println("Invalid phone Number");
        }
        System.out.println("Fill your borrowerIncomeType");
        borrowerIncomeType= scanner.next().toUpperCase();
        String incomeType = "[A-Z]+$";
        pattern = Pattern.compile(incomeType);
        matcher = pattern.matcher(borrowerIncomeType);
        if(matcher.matches()){
            System.out.println("Its a Valid Income Type: "+borrowerIncomeType);
        }else{
            System.out.println("Invalid Income type");
        }
        System.out.println("Dear " + borrowerName + " Thanks for showing interest in the personal loan in my bank your application is submitted  and further details will be mailed to you " + borrowerEmail + "\nmobile number " + mobileNumber + "\nYour Income type  is "+ borrowerIncomeType +".");
    }
}
