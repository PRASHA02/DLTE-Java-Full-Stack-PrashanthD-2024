package technical.review;

import java.util.Scanner;

public class Employee {
   private String firstName;
   private String middleName;
   private String lastName;
   private Integer empID;
   private Long mobileNumber;
   private String emailID;

    public Employee(String firstName, String middleName, String lastName, Integer empID, String permanentAddress, String permanentHouseName, String permanentStreet, String permanentCity, String permanentState, Integer permanentPinCode, String temporaryAddress, String temporaryHouseName, String temporaryStreet, String temporaryCity, String temporaryState, Integer temporaryPinCode, Long mobileNumber, String emailID) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empID = empID;
        this.permanentAddress = permanentAddress;
        this.permanentHouseName = permanentHouseName;
        this.permanentStreet = permanentStreet;
        this.permanentCity = permanentCity;
        this.permanentState = permanentState;
        this.permanentPinCode = permanentPinCode;
        this.temporaryAddress = temporaryAddress;
        this.temporaryHouseName = temporaryHouseName;
        this.temporaryStreet = temporaryStreet;
        this.temporaryCity = temporaryCity;
        this.temporaryState = temporaryState;
        this.temporaryPinCode = temporaryPinCode;
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

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentHouseName() {
        return permanentHouseName;
    }

    public void setPermanentHouseName(String permanentHouseName) {
        this.permanentHouseName = permanentHouseName;
    }

    public String getPermanentStreet() {
        return permanentStreet;
    }

    public void setPermanentStreet(String permanentStreet) {
        this.permanentStreet = permanentStreet;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public Integer getPermanentPinCode() {
        return permanentPinCode;
    }

    public void setPermanentPinCode(Integer permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getTemporaryHouseName() {
        return temporaryHouseName;
    }

    public void setTemporaryHouseName(String temporaryHouseName) {
        this.temporaryHouseName = temporaryHouseName;
    }

    public String getTemporaryStreet() {
        return temporaryStreet;
    }

    public void setTemporaryStreet(String temporaryStreet) {
        this.temporaryStreet = temporaryStreet;
    }

    public String getTemporaryCity() {
        return temporaryCity;
    }

    public void setTemporaryCity(String temporaryCity) {
        this.temporaryCity = temporaryCity;
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public Integer getTemporaryPinCode() {
        return temporaryPinCode;
    }

    public void setTemporaryPinCode(Integer temporaryPinCode) {
        this.temporaryPinCode = temporaryPinCode;
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
}

class EmployeeAddress{

    private String permanentAddress;
    private String permanentHouseName;
    private String permanentStreet;
    private String permanentCity;
    private String permanentState;
    private Integer permanentPinCode;
    private String temporaryAddress;
    private String temporaryHouseName;
    private String temporaryStreet;
    private String temporaryCity;
    private String temporaryState;
    private Integer temporaryPinCode;
}