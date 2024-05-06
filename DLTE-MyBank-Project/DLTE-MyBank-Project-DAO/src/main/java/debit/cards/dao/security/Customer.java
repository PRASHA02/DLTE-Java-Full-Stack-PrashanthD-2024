package debit.cards.dao.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.ResourceBundle;

//entity class for the security
public class Customer implements UserDetails {
    private Integer customerId;
    @NotBlank(message = "{customer.name.null}")
    @Pattern(regexp = "^[a-zA-Z\\\\W_]+$", message = "{customer.name.invalid}")
    private String customerName;
    @NotBlank(message = "{customer.address.null}")
    @Pattern(regexp = "^[a-zA-Z\\\\W_]+$", message = "{customer.address.invalid}")
    private String customerAddress;
    @NotBlank(message = "{customer.status.null}")
    @Pattern(regexp = "^(active|inactive|block)$", message = "{customer.status.invalid}")
    private String customerStatus;
    @NotNull(message = "{customer.contact.null}")
    @Digits(integer = 10,fraction = 0,message = "{customer.contact.invalid}")
    private Long customerContact;
    @NotBlank(message = "{user.name.null}")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "{user.username.invalid}")
    private String username;
    @NotBlank(message = "{password.null}")
    @Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.{8,})\n",message = "{password.invalid}")
    private String password;
    private final int maxAttempt=3;
    private Integer attempts;

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


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }


}
