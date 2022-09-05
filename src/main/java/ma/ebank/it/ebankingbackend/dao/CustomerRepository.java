package ma.ebank.it.ebankingbackend.dao;

import ma.ebank.it.ebankingbackend.bo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Page<Customer> findCustomerByNameContainsIgnoreCase(String name, Pageable pageable);
}
