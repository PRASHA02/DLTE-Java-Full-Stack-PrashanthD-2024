package dao.technical.spring.validations;

import java.util.Scanner;

//validation of backend
public class Validation {

    static Scanner scanner = new Scanner(System.in);


    public boolean isValidateMobile(Long number) {
        String phonePattern = "\\d{10}"; // 10-digit pattern
        return String.valueOf(number).matches(phonePattern);
    }





    public static boolean isValidId(Integer employeeID) {
        return String.valueOf(employeeID).matches("[0-9]+");
    }

    public boolean isValidEmailId(String emailId){
        String email = "^[A-Za-z0-9]{3,}@[A-Za-z]{3,6}\\.[a-z]{2,}$";
        return emailId.matches(email);
    }

     public boolean isValidPin(Integer pin) {
         String pinNumber = "\\d{6}";
        return String.valueOf(pin).matches(pinNumber);
    }

}
