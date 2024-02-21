package basics.services;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BankBonds {
    private Double Maturity;
    private Double interestRate;
    private  Boolean taxStatus;
    private String BankHolder;
    private Integer period;
}
