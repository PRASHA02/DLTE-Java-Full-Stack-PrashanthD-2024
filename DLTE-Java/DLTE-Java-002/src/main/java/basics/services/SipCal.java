package basics.services;

import java.util.Scanner;

import java.math.*;

import static java.lang.Math.pow;

public class SipCal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long invest;
        int years;

        double rate, totalAmount;
        System.out.println("Enter the investment per month");
        invest = sc.nextLong();
        System.out.println("Enter the time duration");
        years = sc.nextInt();
        System.out.println("Enter the interest rate");
        rate = sc.nextDouble();
        rate = rate / (12*100);
        years *=12;
        totalAmount = (invest*((Math.pow(1+rate,years)-1)/rate)*(1+rate));
        System.out.println("The Resultant output is " + totalAmount);
    }
}
