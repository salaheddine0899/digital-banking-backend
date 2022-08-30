package ma.ebank.it.ebankingbackend.dao;

import ma.ebank.it.ebankingbackend.bo.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    Collection<AccountOperation> findAccountOperationByAccount_Id(String id);
    Page<AccountOperation> findAccountOperationByAccount_Id(String id, Pageable pageable);
}
