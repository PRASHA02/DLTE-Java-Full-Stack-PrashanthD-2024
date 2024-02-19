package basics.services;

import java.util.Scanner;

public class MinimumBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //declaration of array
        double[] balance = new double[20];
        //initializatyion of array
        for (int i = 0; i < balance.length; i++) {
            System.out.println("The customer Balance " + (i + 1) + " is ");
            balance[i] = scanner.nextDouble();
        }
            updateBalance(balance);
            //updating customer values
            System.out.println("Updated Customer balances is: ");
            for(int i=0;i<balance.length;i++)
                System.out.println("Customer balances"+(i+1)+" is "+balance[i]);

    scanner.close();
    }
   //updating balances method
   static void updateBalance(double[] balance) {
        for (int i = 0; i < balance.length; i++) {
            if (balance[i] < 10000) {
                if (balance[i] >= 5000 && balance[i] <= 9999) {
                    balance[i] -= balance[i] * 0.03;
                } else if (balance[i] >= 1000 && balance[i]<5000) {
                    balance[i] -= balance[i] * 0.05;
                }
            }
        }
    }
}
