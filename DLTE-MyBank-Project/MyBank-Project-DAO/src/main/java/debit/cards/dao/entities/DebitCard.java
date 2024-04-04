package debit.cards.dao.entities;

import java.util.Date;

public class DebitCard {

    private Long debitCardNumber;
    private Long accountNumber;
    private Integer customerId;
    private Integer debitCardCvv;
    private Integer debitCardPin;
    private Date debitCardExpiry;
    private String debitCardStatus;
    private Double domesticLimit;
    private Double internationalLimit;

    public Long getDebitCardNumber() {
        return debitCardNumber;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "debitCardNumber=" + debitCardNumber +
                ", accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", debitCardCvv=" + debitCardCvv +
                ", debitCardPin=" + debitCardPin +
                ", debitCardExpiry=" + debitCardExpiry +
                ", debitCardStatus='" + debitCardStatus + '\'' +
                ", domesticLimit=" + domesticLimit +
                ", internationalLimit=" + internationalLimit +
                '}';
    }

    public void setDebitCardNumber(Long debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDebitCardCvv() {
        return debitCardCvv;
    }

    public void setDebitCardCvv(Integer debitCardCvv) {
        this.debitCardCvv = debitCardCvv;
    }

    public Integer getDebitCardPin() {
        return debitCardPin;
    }

    public void setDebitCardPin(Integer debitCardPin) {
        this.debitCardPin = debitCardPin;
    }

    public Date getDebitCardExpiry() {
        return debitCardExpiry;
    }

    public void setDebitCardExpiry(Date debitCardExpiry) {
        this.debitCardExpiry = debitCardExpiry;
    }

    public String getDebitCardStatus() {
        return debitCardStatus;
    }

    public void setDebitCardStatus(String debitCardStatus) {
        this.debitCardStatus = debitCardStatus;
    }

    public Double getDomesticLimit() {
        return domesticLimit;
    }

    public void setDomesticLimit(Double domesticLimit) {
        this.domesticLimit = domesticLimit;
    }

    public Double getInternationalLimit() {
        return internationalLimit;
    }

    public void setInternationalLimit(Double internationalLimit) {
        this.internationalLimit = internationalLimit;
    }
}
