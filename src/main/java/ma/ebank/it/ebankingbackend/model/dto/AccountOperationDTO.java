package ma.ebank.it.ebankingbackend.model.dto;

import lombok.Data;
import ma.ebank.it.ebankingbackend.model.enums.OperationType;
import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    String description;
    private OperationType type;
}
