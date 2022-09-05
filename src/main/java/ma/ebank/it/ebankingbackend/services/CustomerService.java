package ma.ebank.it.ebankingbackend.services;

import ma.ebank.it.ebankingbackend.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.Collection;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customer);
    void deleteCustomer(Long customerId);
    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
    Collection<CustomerDTO> listCustomers();

    Collection<CustomerDTO> searchCustomers(String keyword, int page, int size);
}
