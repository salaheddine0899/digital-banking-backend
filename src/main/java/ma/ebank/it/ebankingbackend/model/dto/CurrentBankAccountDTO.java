package ma.ebank.it.ebankingbackend.model.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ma.ebank.it.ebankingbackend.model.enums.AccountStatus;


import java.util.Date;

@Getter @Setter
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private double overDraft;
}
