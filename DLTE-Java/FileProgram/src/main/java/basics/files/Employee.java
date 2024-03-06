package basics.files;

import java.io.Serializable;

public class Employee implements Serializable {
    private String name;
    private Integer empId;
    private Long phoneNumber;

    public Employee() {
        System.out.println("This is an Empty Constructor");
    }

    public Employee(String name, Integer empId, Long phoneNumber) {
        this.name = name;
        this.empId = empId;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", empId=" + empId +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
