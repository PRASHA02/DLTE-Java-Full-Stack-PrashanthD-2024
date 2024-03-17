package basics.services;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "class")
public class Transaction {
    private Date dateOfTransaction;
    private Double amountInTransaction;
    private String toRecipient;
    private String remarks;

    public Transaction() {
    }

    public Transaction(Date dateOfTransaction, Double amountInTransaction, String toRecipient, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.toRecipient = toRecipient;
        this.remarks = remarks;
    }

    @XmlAttribute(name = "Date")
    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @XmlAttribute(name = "Amount")
    public Double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    @XmlAttribute(name="Receipt")
    public String getToRecipient() {
        return toRecipient;
    }

    public void setToRecipient(String toRecipient) {
        this.toRecipient = toRecipient;
    }

    @XmlAttribute(name = "Remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amountInTransaction=" + amountInTransaction +
                ", toRecipient='" + toRecipient + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }


}
