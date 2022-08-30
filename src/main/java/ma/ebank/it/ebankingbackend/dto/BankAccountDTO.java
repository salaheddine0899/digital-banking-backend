package ma.ebank.it.ebankingbackend.dto;

import lombok.Data;
import ma.ebank.it.ebankingbackend.enums.AccountStatus;

import java.util.Date;

@Data
public class BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private String type;
}
