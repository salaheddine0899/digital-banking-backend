package ma.ebank.it.ebankingbackend.mappers;

import ma.ebank.it.ebankingbackend.bo.AccountOperation;
import ma.ebank.it.ebankingbackend.bo.CurrentAccount;
import ma.ebank.it.ebankingbackend.bo.Customer;
import ma.ebank.it.ebankingbackend.bo.SavingAccount;
import ma.ebank.it.ebankingbackend.dto.AccountOperationDTO;
import ma.ebank.it.ebankingbackend.dto.CurrentBankAccountDTO;
import ma.ebank.it.ebankingbackend.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.dto.SavingBankAccountDTO;

public interface BankAccountMapper {
    CustomerDTO fromCustomer(Customer customer);
    Customer fromCustomerDTO(CustomerDTO customerDTO);

    SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount);

    SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingBankAccountDTO);

    CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount);

    CurrentAccount fromCurrentAccountDTO(CurrentBankAccountDTO currentBankAccountDTO);

    AccountOperationDTO fromAccountOperation(AccountOperation operation);

    AccountOperation fromAccountOperationDTO(AccountOperationDTO operationDTO);
}
