package ma.ebank.it.ebankingbackend.model.dto;

import lombok.Data;

import java.util.Collection;
@Data
public class CustomerAccountsDTO {
    private CustomerDTO customer;
    private Collection<BankAccountDTO> accounts;
    private int currentPage;
    private int totalPages;
    private int size;

}
