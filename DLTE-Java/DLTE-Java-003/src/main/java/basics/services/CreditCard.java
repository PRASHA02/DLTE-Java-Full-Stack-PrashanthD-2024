package basics.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

public class CreditCard {
    private Long creditCardNumber;

    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCvv, Integer creditCardLimit, Date dateOfBillGeneration, Date dateOfBillPayment, Integer creditCardPin) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCvv = creditCardCvv;
        this.creditCardLimit = creditCardLimit;
        this.dateOfBillGeneration = dateOfBillGeneration;
        this.dateOfBillPayment = dateOfBillPayment;
        this.creditCardPin = creditCardPin;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", creditCardCvv=" + creditCardCvv +
                ", creditCardLimit=" + creditCardLimit +
                ", dateOfBillGeneration=" + dateOfBillGeneration +
                ", dateOfBillPayment=" + dateOfBillPayment +
                ", creditCardPin=" + creditCardPin +
                '}';
    }

    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCvv;
    private Integer creditCardLimit;
    private Date dateOfBillGeneration;
    private  Date dateOfBillPayment;
    private  Integer creditCardPin;

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public Date getCreditCardExpiry(Date date) {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardCvv(int i) {
        return creditCardCvv;
    }

    public void setCreditCardCvv(Integer creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }

    public Integer getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Date getDateOfBillGeneration() {
        return dateOfBillGeneration;
    }

    public void setDateOfBillGeneration(Date dateOfBillGeneration) {
        this.dateOfBillGeneration = dateOfBillGeneration;
    }

    public Date getDateOfBillPayment() {
        return dateOfBillPayment;
    }

    public void setDateOfBillPayment(Date dateOfBillPayment) {
        this.dateOfBillPayment = dateOfBillPayment;
    }

    public Integer getCreditCardPin() {
        return creditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        this.creditCardPin = creditCardPin;
    }
}
