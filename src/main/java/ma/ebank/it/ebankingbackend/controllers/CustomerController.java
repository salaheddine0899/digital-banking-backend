package ma.ebank.it.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ebank.it.ebankingbackend.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private CustomerService customerService;
    @GetMapping(path = {"","/"})
    public Collection<CustomerDTO> listCustomers(){
        log.info("list of customers");
        return customerService.listCustomers();
    }
    @GetMapping("/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return customerService.getCustomer(customerId);
    }

    @PostMapping(path = {"","/"})
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveCustomer(customerDTO);
    }
    @PutMapping(path = "/{id}")
    public CustomerDTO updateCustomer(@PathVariable(name = "id") Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return customerService.saveCustomer(customerDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long customerId){
        customerService.deleteCustomer(customerId);
    }
}
