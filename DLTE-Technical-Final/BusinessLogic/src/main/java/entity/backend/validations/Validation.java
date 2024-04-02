package entity.backend.validations;

import business.logic.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

//validation of backend
public class Validation {

    static Scanner scanner = new Scanner(System.in);


    public boolean isValidateMobile(Long number) {
        String phonePattern = "\\d{10}"; // 10-digit pattern
        return String.valueOf(number).matches(phonePattern);
    }




//    public Integer validateId(Integer id) {
//        int count =0;
//        String idTwo = "";
//
//        do {
//            try{
////
//                if (isValidId(id.toString())) {
//                    count++;
//                    return id;
//                } else {
//                    System.out.println("Invalid input. Please enter a valid  name containing only letters.");
//                    idTwo = scanner.next();
//                    if (isValidId(idTwo)) {
//                        count++;
//                        return idTwo;
//                    }
//                }
//            } catch (InputMismatchException e) {
//                logger.warn(resourceBundle.getString("string.mismatch"));
//                System.out.println(resourceBundle.getString("string.mismatch"));
//                scanner.next();
//            }
//        } while (true && count==0);
//        if(count==1){
//            return id;
//        }else{
//            return Integer.parseInt(idTwo);
//        }
//    }

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
