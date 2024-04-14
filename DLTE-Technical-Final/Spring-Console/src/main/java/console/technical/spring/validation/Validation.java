package console.technical.spring.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

@Component
public class Validation {
    static Logger logger = LoggerFactory.getLogger(console.technical.spring.SpringConsoleApplication.class);
    static  Scanner scanner = new Scanner(System.in);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public boolean isValidateMobile(String number) {
        String phonePattern = "\\d{10}"; // 10-digit pattern
        return number.matches(phonePattern);

    }

    public boolean isValidId(String employeeID) {
        return employeeID.matches("\\d");
    }

    public boolean validEmailId(String emailId){
        String email = "^[A-Za-z0-9]{3,}@[A-Za-z]{3,6}\\.[a-z]{2,}$";
        return emailId.matches(email);
    }

     public boolean isValidPin(String pin) {
         String pinNumber = "\\d{6}";
        return pin.matches(pinNumber);
    }

    public String validateName() {

        do {
         try{
             String name = scanner.next();
             if (isValidName(name)) {
                 return name;
             } else {
                 System.out.println("Invalid input. Please enter a valid  name containing only letters.");
             }
         } catch (InputMismatchException e) {
             logger.warn(resourceBundle.getString("string.mismatch"));
             System.out.println(resourceBundle.getString("string.mismatch"));
             scanner.next();
         }
        } while (true);
    }

    public static boolean isValidName(String name) {
        String namePattern = "^[a-zA-Z]+$"; // Pattern to match only letters (capital or small)
        return name.matches(namePattern);
    }

}
