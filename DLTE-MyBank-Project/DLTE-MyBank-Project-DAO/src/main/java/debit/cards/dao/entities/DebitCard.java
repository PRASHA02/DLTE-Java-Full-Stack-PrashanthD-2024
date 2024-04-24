package debit.cards.dao.entities;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

//Debit Card Entity
public class DebitCard {

    @NotNull(message= "{card.number.null}")
    @Range(min = 3692468135796670L,max = 9999999999999999L,message = "{card.number.invalid}")
    @Digits(integer=16,fraction = 0,message = "{card.number.invalid}")
    private Long debitCardNumber;

    @NotNull(message= "{account.number.null}")
    @Range(min = 10000000000000L,max = 99999999999999L,message = "{account.number.invalid}")
    @Digits(integer=14,fraction = 0,message = "{card.number.invalid}")
    private Long accountNumber;

//    @NotNull(message= "{customer.id.null}")
//    @Digits(integer =6,fraction = 0,message = "{customer.id.invalid}")
    private Integer customerId;

    @NotNull(message = "{card.cvv.null}")
    @Digits(integer = 3, fraction = 0, message = "{card.cvv.invalid}")
    @Positive(message = "{positive.number}")
    private Integer debitCardCvv;

    @NotNull(message = "{card.pin.null}")
    @Digits(integer = 4,fraction = 0,message = "{card.pin.invalid}")
    @Positive(message = "{positive.number}")
    private Integer debitCardPin;

    @NotNull(message = "{card.expiry.null}")
    @FutureOrPresent(message = "{date.expiry}")
    private Date debitCardExpiry;
    //pattern perform regex validations
    @NotNull(message = "{card.status.null}")
    @Pattern(regexp = "^(active|inactive)$", message = "{card.status.invalid}")
    private String debitCardStatus;

    @Range(min = 100L,max=100000L,message = "{card.domestic.limit}")
    @NotNull(message = "{card.domestic.null}")
    @Positive(message = "{positive.number}")
    private Double domesticLimit;

    @Range(min = 100L,max=50000L,message = "{card.international.limit}")
    @NotNull(message = "{card.international.null}")
    @Positive(message = "{positive.number}")
    private Double internationalLimit;

    public Long getDebitCardNumber() {
        return debitCardNumber;
    }

    public DebitCard() {
    }

    public DebitCard(@NotNull(message = "{card.number.null}") @Digits(integer = 16, fraction = 0, message = "{card.number.invalid}") Long debitCardNumber, @NotNull(message = "{account.number.null}") @Digits(integer = 14, fraction = 0, message = "{account.number.invalid}") Long accountNumber, @NotNull(message = "{customer.id.null}") @Digits(integer = 6, fraction = 0, message = "{customer.id.invalid}") Integer customerId, @NotNull(message = "{card.cvv.null}") @Digits(integer = 3, fraction = 0, message = "{card.cvv.invalid}") Integer debitCardCvv, @NotNull(message = "{card.pin.null}") @Digits(integer = 4, fraction = 0, message = "{card.pin.invalid}") Integer debitCardPin, @NotNull(message = "{card.expiry.null}") Date debitCardExpiry, @NotNull(message = "{card.status.null}") @Pattern(regexp = "^[a-zA-Z]+$", message = "{card.status.invalid}") String debitCardStatus, @NotNull(message = "{card.domestic.null}") Double domesticLimit, @NotNull(message = "{card.international.null}") Double internationalLimit) {
        this.debitCardNumber = debitCardNumber;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.debitCardCvv = debitCardCvv;
        this.debitCardPin = debitCardPin;
        this.debitCardExpiry = debitCardExpiry;
        this.debitCardStatus = debitCardStatus;
        this.domesticLimit = domesticLimit;
        this.internationalLimit = internationalLimit;
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
