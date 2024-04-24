package element.spring.boot.springboot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TransactionEntity implements Serializable {

    @Id
    private Long transactionId;
    private String username;
    private Long accountId;
    private Long amount;
    private String transactionType; //deposit,withdraw

    public TransactionEntity() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionEntity(Long transactionId, String username, Long accountId, Long amount, String transactionType) {
        this.transactionId = transactionId;
        this.username = username;
        this.accountId = accountId;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "transactionId=" + transactionId +
                ", username='" + username + '\'' +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
