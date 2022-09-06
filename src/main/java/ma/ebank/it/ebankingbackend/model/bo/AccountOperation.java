package ma.ebank.it.ebankingbackend.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ebank.it.ebankingbackend.model.enums.OperationType;

import javax.persistence.*;
import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;
    String description;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private BankAccount account;
}
