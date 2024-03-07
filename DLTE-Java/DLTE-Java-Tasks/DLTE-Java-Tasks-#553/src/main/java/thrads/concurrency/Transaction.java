package thrads.concurrency;

import java.util.Date;

public class Transaction {
    private Date transactionDate;
    private Double amountInTransaction;
    private String sentTo;
    private String remarks;

    public Transaction() {
    }

    public Transaction(Date transactionDate, Double amountInTransaction, String sentTo, String remarks) {
        this.transactionDate = transactionDate;
        this.amountInTransaction = amountInTransaction;
        this.sentTo = sentTo;
        this.remarks = remarks;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionDate=" + transactionDate +
                ", amountInTransaction=" + amountInTransaction +
                ", sentTo='" + sentTo + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }


}
