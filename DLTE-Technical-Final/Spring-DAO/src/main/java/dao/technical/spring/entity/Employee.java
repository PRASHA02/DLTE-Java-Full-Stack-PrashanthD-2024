package dao.technical.spring.entity;
//backend Employee entity
public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer empID;
    private Long mobileNumber;
    private String emailID;
    private EmployeeAddress temporaryAddress,permanentAddress;
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

    public Employee(String firstName, String middleName, String lastName, Integer empID, Long mobileNumber, String emailID, EmployeeAddress temporaryAddress, EmployeeAddress permanentAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empID = empID;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.temporaryAddress = temporaryAddress;
        this.permanentAddress = permanentAddress;
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

    public EmployeeAddress getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddress temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public EmployeeAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empID=" + empID +
                ", mobileNumber=" + mobileNumber +
                ", emailID='" + emailID + '\'' +
                ", temporaryAddress=" + temporaryAddress +
                ", permanentAddress=" + permanentAddress +
                '}';
    }
}
