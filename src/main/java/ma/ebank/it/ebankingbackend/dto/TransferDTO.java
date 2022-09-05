package ma.ebank.it.ebankingbackend.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private String accountIdSource;
    private String accountIdDestination;
    private double amount;
    private String description;
}
