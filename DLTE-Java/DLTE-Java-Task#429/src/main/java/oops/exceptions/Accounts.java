package oops.exceptions;

public class Accounts {
    private Long accountNumber;
    private Double accountBalance;
    private String accountHolder;

    public Accounts(Long accountNumber, Double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

    public Accounts(){
        System.out.println("Welcome to Mobile Banking");
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", accountHolder='" + accountHolder + '\'' +
                '}';
    }
}
