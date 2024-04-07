package debit.cards.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//Customer Entity
public class Customer {

    @NotNull(message = "{customer.id.null}")
    @Digits(integer = 10, fraction = 0, message = "{customer.id.invalid}")
    private Integer customerId;

    @NotBlank(message = "{customer.name.null}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{card.status.invalid}")
    private String customerName;

    @NotBlank(message = "{customer.address.null}")
    private String customerAddress;

    @NotBlank(message = "{customer.status.null}")
    @Pattern(regexp = "^(active|inactive|Active|Inactive|ACTIVE|INACTIVE)$", message = "{card.status.invalid}")
    private String customerStatus;

    @NotNull(message = "{customer.contact.null}")
    @Digits(integer = 10,fraction = 0,message = "{customer.contact.invalid}")
    private Long customerContact;

    @NotBlank(message = "{user.name.null}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "{user.username.invalid}")
    private String username;

    @NotBlank(message = "{password.null}")
    private String password;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
