package debit.cards.dao.entities;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

//Account Entity
public class Account {
   //Bean Validation
    //Not Null Checks for Null value Validation
    @NotNull(message = "{account.id.null}")
    @Digits(integer = 6, fraction = 0, message = "{account.num.valid}")
    private Integer accountId;

    //Digits for digit validation
    @NotNull(message = "{customer.id.null}")
    @Digits(integer = 6, fraction = 0, message = "{customer.id.invalid}")
    private Integer customerId;
    //No blank Validation is for blank inputs
    @NotBlank(message = "{account.type.null}")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "{account.type.invalid}")
    private String accountType;

    @NotNull(message= "{account.number.null}")
    @Range(min = 10000000000000L,max = 99999999999999L,message = "{account.number.invalid}")
    @Digits(integer=14,fraction = 0,message = "{account.number.invalid}")
    private Long accountNumber;

    @NotBlank(message = "{account.status.null}")
    @Pattern(regexp = "^(active|inactive)$", message = "{card.status.invalid}")
    private String accountStatus;

    @Range(min = 0)
    @NotNull(message = "{account.balance.null}")
    @Positive(message = "{positive.number}")
    private Double accountBalance;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
