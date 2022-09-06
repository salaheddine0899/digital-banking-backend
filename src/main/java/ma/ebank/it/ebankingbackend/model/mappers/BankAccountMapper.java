package ma.ebank.it.ebankingbackend.model.mappers;

import ma.ebank.it.ebankingbackend.model.bo.AccountOperation;
import ma.ebank.it.ebankingbackend.model.bo.CurrentAccount;
import ma.ebank.it.ebankingbackend.model.bo.Customer;
import ma.ebank.it.ebankingbackend.model.bo.SavingAccount;
import ma.ebank.it.ebankingbackend.model.dto.AccountOperationDTO;
import ma.ebank.it.ebankingbackend.model.dto.CurrentBankAccountDTO;
import ma.ebank.it.ebankingbackend.model.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.model.dto.SavingBankAccountDTO;

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
