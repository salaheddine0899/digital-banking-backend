package ma.ebank.it.ebankingbackend.model.dto;

import lombok.*;

@Data @Getter @Setter
public class SavingBankAccountDTO extends BankAccountDTO{
    private double interestRate;
}
