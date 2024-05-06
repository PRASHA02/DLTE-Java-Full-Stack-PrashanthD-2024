package debit.cards.dao.entities;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

//Debit Card Entity
public class DebitCard {

    @NotNull(message= "{VAL001}")
    @Range(min = 3692468135796670L,max = 9999999999999999L,message = "{VAL012}")
    @Digits(integer=16,fraction = 0,message = "{VAL012}")
    private Long debitCardNumber;

    @NotNull(message= "{VAL002}")
    @Range(min = 10000000000000L,max = 99999999999999L,message = "{VAL013}")
    @Digits(integer=14,fraction = 0,message = "{VAL013}")
    private Long accountNumber;

    private Integer customerId;

    private Integer debitCardCvv;

    private Integer debitCardPin;

    @NotNull(message = "{VAL003}")
    @Future(message = "{VAL004}")
    private Date debitCardExpiry;

    @NotNull(message = "{VAL005}")
    @Pattern(regexp = "^(active)$", message = "{VAL006}")
    private String debitCardStatus;

    @Range(min = 100L,max=100000L,message = "{VAL007}")
    @NotNull(message = "{VAL008}")
    @Positive(message = "{VAL009}")
    private Double domesticLimit;

    @Range(min = 100L,max=50000L,message = "{VAL010}")
    @NotNull(message = "{VAL011}")
    @Positive(message = "{VAL009}")
    private Double internationalLimit;

    public Long getDebitCardNumber() {
        return debitCardNumber;
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
