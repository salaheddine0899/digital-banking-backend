package ma.ebank.it.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ebank.it.ebankingbackend.bo.Customer;
import ma.ebank.it.ebankingbackend.dao.CustomerRepository;
import ma.ebank.it.ebankingbackend.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.mappers.BankAccountMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private BankAccountMapper bankAccountMapper;
    private CustomerRepository customerRepository;
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        log.info("Saving customer");
        return bankAccountMapper.fromCustomer(
                customerRepository.save(bankAccountMapper.fromCustomerDTO(customer)));

    }
    @Override
    public void deleteCustomer(Long customerId){
        log.info("deleting customer");
        customerRepository.deleteById(customerId);
    }


    @Override
    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
        return bankAccountMapper.fromCustomer(customer);
    }

    @Override
    public Collection<CustomerDTO> listCustomers() {
        Collection<Customer> customers = customerRepository.findAll();
        Collection<CustomerDTO> customersDto = customers.stream().map(customer -> bankAccountMapper.fromCustomer(customer)).collect(Collectors.toList());
        return customersDto;
    }
}
