package basics.services;

import java.util.Scanner;

public class DebitTransaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long balance;
        int debit=0;
        System.out.println("Enter the transaction "+1);
         long temp = scanner.nextLong();
        for(int i=2;i<=10;i++){
            System.out.println("Enter the transaction "+i);
            balance = scanner.nextLong();
            if(temp>balance) {
                debit = debit + 1;
            }
            temp = balance;
        }
        System.out.println("The debit of first 10 Transcation is "+debit);

    }
}
