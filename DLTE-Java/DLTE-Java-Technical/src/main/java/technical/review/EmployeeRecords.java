package technical.review;

import java.util.Scanner;

public class EmployeeRecords{
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        System.out.println("The Employee Details are:- ");
        System.out.println("Employee Name: "+ employee.getName());
        System.out.println("Employee ID: "+ employee.getEmpID());
        System.out.println("Employee Permanent Address: "+ employee.getPermanentAddress());
        System.out.println("Employee Temporary Address: "+ employee.getTemporaryAddress());
        scanner.close();
    }
    static void employeeDetails(){
        System.out.println("Enter the Employee first Name");
        String firstName = scanner.nextLine().toUpperCase();
        System.out.println("Enter the Employee middle Name");
        String middleName = scanner.nextLine().toUpperCase();
        System.out.println("Enter the Employee last Name");
        String lastName = scanner.nextLine().toUpperCase();
        System.out.println("Enter the Employee permanent address");
        String permanentAddress = scanner.nextLine().toUpperCase();
        String permanentHouse = scanner.nextLine();
        String
        System.out.println("Enter the Employee Temporary address address");
        String temporaryAddress = scanner.nextLine().toUpperCase();
        System.out.println("Enter the Employee ID");
        Integer empID = scanner.nextInt();
        System.out.println("Enter the Employee Mobile Number");
        Long mobileNumber = scanner.nextLong();
        System.out.println("The Employee Details are:- ");
        System.out.println("Employee Name: "+ employee.getName());
        System.out.println("Employee ID: "+ employee.getEmpID());
        System.out.println("Employee Permanent Address: "+ employee.getPermanentAddress());
        System.out.println("Employee Temporary Address: "+ employee.getTemporaryAddress());
    }
}

//import java.util.Scanner;
//
//public class Employee {
//   private String name;
//   private Integer empID;
//   private String permanentAddress;
//   private String temporaryAddress;
//
//    public String getName() {
//        return name;
//    }
//
//    public Integer getEmpID() {
//        return empID;
//    }
//
//    public void setEmpID(Integer empID) {
//        this.empID = empID;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPermanentAddress() {
//        return permanentAddress;
//    }
//
//    public void setPermanentAddress(String permanentAddress) {
//        this.permanentAddress = permanentAddress;
//    }
//
//    public String getTemporaryAddress() {
//        return temporaryAddress;
//    }
//
//    public void setTemporaryAddress(String temporaryAddress) {
//        this.temporaryAddress = temporaryAddress;
//    }
//
//    public Employee(String name, Integer empID, String permanentAddress, String temporaryAddress) {
//        this.name = name;
//        this.empID = empID;
//        this.permanentAddress = permanentAddress;
//        this.temporaryAddress = temporaryAddress;
//    }
//}