package ma.ebank.it.ebankingbackend.model.dto;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private double amount;
    private String description;
}
