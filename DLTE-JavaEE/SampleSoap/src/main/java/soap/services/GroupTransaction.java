package soap.services;

import java.util.List;

public class GroupTransaction {
    List<Transaction> transactionList;

    public GroupTransaction() {
    }

    public GroupTransaction(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "groupTransaction{" +
                "transactionList=" + transactionList +
                '}';
    }
}
