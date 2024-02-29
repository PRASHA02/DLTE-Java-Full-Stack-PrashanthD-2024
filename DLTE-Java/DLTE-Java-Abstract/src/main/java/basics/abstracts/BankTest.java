package basics.abstracts;

import java.util.Scanner;

public class BankTest implements Bank {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BankTest test = new BankTest();
        test.about();
        test.account();
    }


    @Override
    public void account() {
        System.out.println("Enter the Account Number: ");
        Long accountNumber = scanner.nextLong();
        System.out.println("Enter the Account Holder Name: ");
        String holderName = scanner.next();
        System.out.println("The Account holder name is "+holderName+" and his account number is "+accountNumber);
    }

    @Override
    public void about() {
        System.out.println("Enter the Account Holder Name: ");
        String name = scanner.next();
        System.out.println("Enter the gender: ");
        char ch = scanner.next().charAt(0);
        System.out.println(name+" is a "+ch);
    }
}
