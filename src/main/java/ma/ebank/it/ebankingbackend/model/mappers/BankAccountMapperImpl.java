package ma.ebank.it.ebankingbackend.model.mappers;


import ma.ebank.it.ebankingbackend.model.bo.AccountOperation;
import ma.ebank.it.ebankingbackend.model.bo.CurrentAccount;
import ma.ebank.it.ebankingbackend.model.bo.Customer;
import ma.ebank.it.ebankingbackend.model.bo.SavingAccount;
import ma.ebank.it.ebankingbackend.model.dto.AccountOperationDTO;
import ma.ebank.it.ebankingbackend.model.dto.CurrentBankAccountDTO;
import ma.ebank.it.ebankingbackend.model.dto.CustomerDTO;
import ma.ebank.it.ebankingbackend.model.dto.SavingBankAccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl implements BankAccountMapper {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO= new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return  customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }
    @Override
    public SavingBankAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        savingBankAccountDTO.setCustomer(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return  savingBankAccountDTO;
    }
    @Override
    public SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomer()));
        return savingAccount;
    }
    @Override
    public CurrentBankAccountDTO fromCurrentAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }
    @Override
    public CurrentAccount fromCurrentAccountDTO(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomer()));
        return currentAccount;
    }
    @Override
    public AccountOperationDTO fromAccountOperation(AccountOperation operation){
        AccountOperationDTO operationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(operation,operationDTO);
        return operationDTO;
    }
    @Override
    public AccountOperation fromAccountOperationDTO(AccountOperationDTO operationDTO){
        AccountOperation operation=new AccountOperation();
        BeanUtils.copyProperties(operationDTO,operation);
        return operation;
    }

}
