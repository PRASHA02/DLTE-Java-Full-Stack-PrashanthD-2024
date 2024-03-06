package technical.review;

import java.io.Serializable;
import java.util.Scanner;

public class Employee implements Serializable {
   private String firstName;
   private String middleName;
   private String lastName;
   private Integer empID;
   private Long mobileNumber;
   private String emailID;
    public Employee() {
    }


    public Employee(String firstName, String middleName, String lastName, Integer empID,  Long mobileNumber, String emailID) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empID = empID;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @Override
    public String toString() {
        return "" +
                "Employee Name: " + firstName +" "+middleName+" "+lastName+ '\n' +
                "Employee ID: " + empID + '\n'+
                "Employee Mobile Number: " + mobileNumber + '\n'+
                "Employee Email ID: " + emailID
                ;
    }
}

