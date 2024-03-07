package basics.services;

import java.util.Scanner;

public class HealthInsurance {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java HealthInsurance <insurance_type>");
            return;
        }
        String[] health = {"health","life","medicine"};
        String matchingString = args[0];
        for (int i = 0; i < health.length; i++) {
            if (health[i].equalsIgnoreCase(matchingString)) {
                System.out.println("Your health insurance position is " + i);
                return;
            }
        }
        System.out.println("Health insurance position is not found");
    }
}
