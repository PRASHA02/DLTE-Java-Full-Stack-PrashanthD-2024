package generics.credit;

import java.util.Date;

public class CreditCard {
    private Long cardNumber;
    private Integer creditCvv;
    private Integer creditPin;
    private Date creditExpiry;

    public CreditCard() {
    }

    public CreditCard(Long cardNumber, Integer creditCvv, Integer creditPin, Date creditExpiry) {
        this.cardNumber = cardNumber;
        this.creditCvv = creditCvv;
        this.creditPin = creditPin;
        this.creditExpiry = creditExpiry;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCreditCvv() {
        return creditCvv;
    }

    public void setCreditCvv(Integer creditCvv) {
        this.creditCvv = creditCvv;
    }

    public Integer getCreditPin() {
        return creditPin;
    }

    public void setCreditPin(Integer creditPin) {
        this.creditPin = creditPin;
    }

    public Date getCreditExpiry() {
        return creditExpiry;
    }

    public void setCreditExpiry(Date creditExpiry) {
        this.creditExpiry = creditExpiry;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", creditCvv=" + creditCvv +
                ", creditPin=" + creditPin +
                ", creditExpiry=" + creditExpiry +
                '}';
    }
}
