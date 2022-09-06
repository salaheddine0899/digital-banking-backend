package ma.ebank.it.ebankingbackend.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@DiscriminatorValue(value = "SA")
public class SavingAccount extends BankAccount{
    private double interestRate;
}
