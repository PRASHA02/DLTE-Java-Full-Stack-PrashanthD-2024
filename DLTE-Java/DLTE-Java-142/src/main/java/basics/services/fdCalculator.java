package basics.services;
import java.util.*;
public class fdCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int time;
        long principle;
        double rate;
        System.out.println("Hello, Welcome to FD Calculator");
        System.out.println("The principle amount is ");
        principle = sc.nextLong();
        System.out.println("Enter the rate");
        rate = sc.nextDouble();
        System.out.println("Enter the duration time in years");
        time = sc.nextInt();
        double maturityValue = (principle + (principle*rate*time/100));
        System.out.println("The Maturity Value is "+maturityValue);

    }
}
