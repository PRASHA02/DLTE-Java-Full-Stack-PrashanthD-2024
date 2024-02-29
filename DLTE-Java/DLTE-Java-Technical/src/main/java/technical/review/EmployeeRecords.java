package technical.review;

import java.util.Scanner;

public class EmployeeRecords {
    static Scanner scanner = new Scanner(System.in);

    private Employee employee;
    private EmployeeAddress employeePermanentAddress;
    private EmployeeAddress employeeTemporaryAddress;

    public static void main(String[] args) {
        System.out.println("---Welcome to My Bank---");
        System.out.println("Enter the number of employee Details you want to insert");
        int numberOfEmployee = scanner.nextInt();
        EmployeeRecords employeeRecords = new EmployeeRecords();

        employeeRecords.employeeDetails();
//        employeeRecords.employeePermanentAddress();
//        employeeRecords.employeeTemporaryAddress();
        employeeRecords.employeeOutputDetails();
//        employeeRecords.employeePermanentOutputDetails();
//        employeeRecords.employeeTemporaryOutputDetails();

        scanner.close();
    }
    public void employeeDetails(){
        System.out.println("Enter the Employee first Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter the Employee middle Name");
        String middleName = scanner.nextLine();
        System.out.println("Enter the Employee last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter the Employee ID");
        Integer empID = scanner.nextInt();
        System.out.println("Enter the Employee Mobile Number");
        Long mobileNumber = scanner.nextLong();
        System.out.println("Enter the Employee email ID");
        String emailID = scanner.next();
        employee = new Employee(firstName,middleName,lastName,empID,mobileNumber,emailID);
    }
    public void employeePermanentAddress(){
        scanner.nextLine();
        System.out.println("Enter the Employee permanent address");
        String permanentAddress = scanner.nextLine();
        System.out.println("Enter the Employee permanent House Name");
        String permanentHouse = scanner.nextLine();
        System.out.println("Enter the Employee permanent Street Name");
        String permanentStreet = scanner.nextLine();
        System.out.println("Enter the Employee permanent city Name");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter the Employee permanent State Name");
        String permanentState = scanner.nextLine();
        System.out.println("Enter the Employee permanent Pin Code");
        Integer permanentPinCode = scanner.nextInt();
        employeePermanentAddress = new EmployeeAddress(permanentAddress,permanentHouse,permanentStreet,permanentCity,permanentState,permanentPinCode);
    }
    public void employeeTemporaryAddress(){
        scanner.nextLine();
        System.out.println("Enter the Employee Temporary address");
        String temporaryAddress = scanner.nextLine();
        System.out.println("Enter the Employee Temporary House Name");
        String temporaryHouse = scanner.nextLine();
        System.out.println("Enter the Employee Temporary Street Name");
        String temporaryStreet = scanner.nextLine();
        System.out.println("Enter the Employee Temporary city Name");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter the Employee Temporary State Name");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter the Employee Temporary Pin Code");
        Integer temporaryPinCode = scanner.nextInt();
        employeeTemporaryAddress = new EmployeeAddress(temporaryAddress,temporaryHouse,temporaryStreet,temporaryCity,temporaryState,temporaryPinCode);

    }
    public void employeeOutputDetails() {
        System.out.println("\nThe Employee Details are:- ");
        System.out.println(employee.toString());
    }

    public void employeePermanentOutputDetails(){
        System.out.println("\nEmployee Permanent Details ");
        System.out.println(employeePermanentAddress.toString());
    }

    public void employeeTemporaryOutputDetails(){
        System.out.println("\nEmployee Temporary Details ");
        System.out.println(employeeTemporaryAddress.toString());
    }
}

