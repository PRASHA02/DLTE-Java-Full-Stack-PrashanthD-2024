package basics.project.entities;

import java.util.ArrayList;

public class UserInformation {
    private String userName;
    private  String password;
    private String address;
    private String Email;
    private String contactInformation;
    private Double balance;

    ArrayList<String> transactions;

    public UserInformation() {
    }

    public UserInformation(String userName, String password, String address, String email, String contactInformation, Double balance,ArrayList<String> transactions) {
        this.userName = userName;
        this.password = password;
        this.address = address;
        Email = email;
        this.contactInformation = contactInformation;
        this.balance = balance;
        this.transactions = transactions;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}


