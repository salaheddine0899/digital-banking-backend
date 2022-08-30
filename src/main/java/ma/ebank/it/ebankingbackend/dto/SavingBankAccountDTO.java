package ma.ebank.it.ebankingbackend.dto;

import lombok.*;

@Data @Getter @Setter
public class SavingBankAccountDTO extends BankAccountDTO{
    private double interestRate;
}
