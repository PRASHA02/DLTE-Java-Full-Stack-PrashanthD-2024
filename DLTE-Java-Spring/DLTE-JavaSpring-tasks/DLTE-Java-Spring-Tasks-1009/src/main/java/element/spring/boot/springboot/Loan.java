package element.spring.boot.springboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

public class Loan {
    private Integer loanNumber;
    private Double loanAmount;
    private String loanDate;
    private ArrayList<Integer> number;
    private String loanType;
    private String borrowerName;
    private Long borrowerContact;
    public Loan() {
    }

    public Loan(Integer loanNumber, Double loanAmount, String loanDate, String loanType, String borrowerName, Long borrowerContact,ArrayList<Integer> number) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanType = loanType;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
        this.number = number;
    }

    public ArrayList<Integer> getNumber() {
        return number;
    }

    public void setNumber(ArrayList<Integer> number) {
        this.number = number;
    }

    public Integer getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Integer loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", number=" + number +
                ", loanType='" + loanType + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                '}';
    }
}
