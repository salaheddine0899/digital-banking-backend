package ma.ebank.it.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ebank.it.ebankingbackend.bo.Customer;
import ma.ebank.it.ebankingbackend.dao.CustomerRepository;
import ma.ebank.it.ebankingbackend.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.mappers.BankAccountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
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

    @Override
    public Collection<CustomerDTO> searchCustomers(String keyword, int page, int size){
        Page<Customer> customersPage = customerRepository.findCustomerByNameContainsIgnoreCase(keyword, PageRequest.of(page, size));
        Collection<Customer> customers=customersPage.getContent();
        List<CustomerDTO> customerDTOS = customers.stream().map(customer -> bankAccountMapper.fromCustomer(customer)).collect(Collectors.toList());
        return  customerDTOS;
    }
}
