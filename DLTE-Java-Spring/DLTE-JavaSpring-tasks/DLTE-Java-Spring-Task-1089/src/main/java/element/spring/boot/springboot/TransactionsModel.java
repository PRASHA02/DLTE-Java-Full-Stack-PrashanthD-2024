package element.spring.boot.springboot;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;

public class TransactionsModel {
    @NotNull(message = "${id.not.null}")
    @Digits(integer=6,fraction = 0,message = "{id.number}")
    private Long transactionId;
    @NotNull(message = "${date.not.null}")
    private Date transactionDate;
    @NotNull(message = "${by.not.null}")
    @Pattern(regexp = "[a-zA-Z]+$",message = "{by.string}")
    private String transactionBy;
    @NotNull(message = "${to.not.null}")
    @Pattern(regexp = "[a-zA-Z]+$",message = "{to.string}")
    private String transactionTo;
    @NotNull(message = "${amount.not.null}")
    @Digits(integer=20,fraction = 0,message = "{amount.number}")
    private Long transactionAmount;
    @NotNull(message = "${for.not.null}")
    @Pattern(regexp = "[a-zA-Z]+$",message = "{for.string}")
    private String transactionFor;

    public TransactionsModel() {
    }

    public TransactionsModel(Long transactionId, Date transactionDate, String transactionBy, String transactionTo, Long transactionAmount, String transactionFor) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionFor = transactionFor;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionFor() {
        return transactionFor;
    }

    public void setTransactionFor(String transactionFor) {
        this.transactionFor = transactionFor;
    }

    @Override
    public String toString() {
        return "TransactionsModel{" +
                "transactionId=" + transactionId +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionFor='" + transactionFor + '\'' +
                '}';
    }
}
