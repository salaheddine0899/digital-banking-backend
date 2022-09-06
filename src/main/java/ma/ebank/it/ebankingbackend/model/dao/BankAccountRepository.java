package ma.ebank.it.ebankingbackend.model.dao;

import ma.ebank.it.ebankingbackend.model.bo.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    Page<BankAccount> findByCustomerIdOrderByCreatedAtDesc(Long id, Pageable pageable);
}
