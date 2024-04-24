package element.spring.boot.springboot.models;

import javax.persistence.*;
import java.io.Serializable;

//model entity
@Entity
@Table(name = "account_info")
public class AccountInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long accountNumber;
    private String name;
    private String type;
    private String password;
    private String email;
    private Long phone;

    public AccountInfo() {
    }

    public AccountInfo(Long accountNumber, String name, String type, String password, String email, Long phone) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.type = type;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}

