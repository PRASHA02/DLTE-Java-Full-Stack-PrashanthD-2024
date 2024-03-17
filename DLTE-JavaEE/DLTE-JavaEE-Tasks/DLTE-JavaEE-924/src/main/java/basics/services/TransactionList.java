package basics.services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Transaction")
public class TransactionList {
    private List<Transaction> transactions;

    public TransactionList() {
    }

    public TransactionList(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    @XmlElement(name="TransactionList")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionList{" +
                "transactions=" + transactions +
                '}';
    }
}