package ma.ebank.it.ebankingbackend.dao;

import ma.ebank.it.ebankingbackend.bo.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
