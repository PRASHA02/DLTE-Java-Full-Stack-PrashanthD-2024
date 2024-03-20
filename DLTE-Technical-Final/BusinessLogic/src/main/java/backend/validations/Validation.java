package backend.validations;

public class Validation {

    public boolean isValidateMobile(String number) {
        String phonePattern = "\\d{10}"; // 10-digit pattern
        return number.matches(phonePattern);

    }

    public boolean isValidId(String employeeID) {
        return employeeID.matches("\\d");
    }

    public boolean isValidEmailId(String emailId){
        String email = "^[A-Za-z0-9]{3,}@[A-Za-z]{3,6}\\.[a-z]{2,}$";
        return emailId.matches(email);
    }

     public boolean isValidPin(String pin) {
         String pinNumber = "\\d{6}";
        return pin.matches(pinNumber);
    }

}
