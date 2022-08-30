package ma.ebank.it.ebankingbackend.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@DiscriminatorValue(value = "CA")
public class CurrentAccount extends BankAccount{
    private double overDraft;
}
