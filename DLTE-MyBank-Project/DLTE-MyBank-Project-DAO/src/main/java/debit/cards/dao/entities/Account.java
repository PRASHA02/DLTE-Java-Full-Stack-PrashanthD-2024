package debit.cards.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    private String accountType;

    @NotNull(message = "{account.number.null}")
    @Digits(integer = 14, fraction = 0, message = "{account.number.invalid}")
    private Long accountNumber;

    @NotBlank(message = "{account.status.null}")
    @Pattern(regexp = "^(active|inactive|Active|Inactive|ACTIVE|INACTIVE)$", message = "{card.status.invalid}")
    private String accountStatus;

    @NotNull(message = "{account.balance.null}")
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
