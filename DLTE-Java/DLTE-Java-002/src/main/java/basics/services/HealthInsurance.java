package basics.services;

import java.util.Scanner;

public class HealthInsurance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] health = {"health","life","medicine"};
        System.out.println("Enter your health insurance :");
        String matchingString = scanner.nextLine();
        for(int i=0;i<health.length;i++){
            if(health[i].equalsIgnoreCase(matchingString)){
                System.out.println("Your health insurance position is "+i);
                scanner.close();
                return;
            }
        }
        System.out.println("Health insurance position is not found");
        scanner.close();
    }
}
