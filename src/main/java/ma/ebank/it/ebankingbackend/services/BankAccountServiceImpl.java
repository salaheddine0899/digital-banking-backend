package ma.ebank.it.ebankingbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.ebank.it.ebankingbackend.bo.*;
import ma.ebank.it.ebankingbackend.dao.AccountOperationRepository;
import ma.ebank.it.ebankingbackend.dao.BankAccountRepository;
import ma.ebank.it.ebankingbackend.dao.CustomerRepository;
import ma.ebank.it.ebankingbackend.dto.*;
import ma.ebank.it.ebankingbackend.enums.AccountStatus;
import ma.ebank.it.ebankingbackend.enums.OperationType;
import ma.ebank.it.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.ebank.it.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.mappers.BankAccountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository operationRepository;
    private BankAccountMapper bankAccountMapper;
    private CustomerRepository customerRepository;
    void createBankAccount(double initialBalance, Long customerId, BankAccount bankAccount, String accountId,
                           Date createdDate, AccountStatus accountStatus) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("Customer not found");
        if(accountId==null)
            bankAccount.setId(UUID.randomUUID().toString());
        else bankAccount.setId(accountId);
        if(createdDate==null)
        bankAccount.setCreatedAt(new Date());
        else bankAccount.setCreatedAt(createdDate);
        bankAccount.setBalance(initialBalance);
        bankAccount.setCustomer(customer);
        if(accountStatus==null)
        bankAccount.setStatus(AccountStatus.CREATED);
        else bankAccount.setStatus(accountStatus);

    }

    @Override
    public SavingBankAccountDTO createSavingBankAccount(double initialBalance, Long customerId, double interestRate, String accountId, Date date, AccountStatus accountStatus) throws CustomerNotFoundException {
        SavingAccount savingAccount=new SavingAccount();
        createBankAccount(initialBalance,customerId,savingAccount,accountId,date,accountStatus);
        savingAccount.setInterestRate(interestRate);
        SavingAccount savedBankAccount=bankAccountRepository.save(savingAccount);
        return bankAccountMapper.fromSavingAccount(savedBankAccount);
    }

    @Override
    public CurrentBankAccountDTO createCurrentBankAccount(double initialBalance, Long customerId, double overdraft, String accountId, Date date, AccountStatus accountStatus) throws CustomerNotFoundException {
        CurrentAccount currentAccount=new CurrentAccount();
        createBankAccount(initialBalance,customerId,currentAccount,accountId,date,accountStatus);
        currentAccount.setOverDraft(overdraft);
        CurrentAccount savedBankAccount=bankAccountRepository.save(currentAccount);
        return bankAccountMapper.fromCurrentAccount(savedBankAccount);
    }
    @Override
    public BankAccountDTO getBankAccount(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException("bank account not found"));
        if(bankAccount instanceof  CurrentAccount)
            return bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
        else
            return bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
    }

    @Override
    public Collection<BankAccountDTO> bankAccountList(){
        Collection<BankAccount> bankAccounts=bankAccountRepository.findAll();
        Collection<BankAccountDTO> bankAccountDTOS=bankAccounts.stream().map(bankAccount->{
            if(bankAccount instanceof CurrentAccount) {
                CurrentBankAccountDTO currentBankAccountDTO = bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
                return currentBankAccountDTO;
            }
            else{
                SavingBankAccountDTO savingBankAccountDTO = bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
                return savingBankAccountDTO;
            }
        }).collect(Collectors.toList());
        return bankAccountDTOS;
    }

    void saveOperation(double amount, BankAccount bankAccount, String description, AccountOperation operation){
        operation.setAmount(amount);
        operation.setAccount(bankAccount);
        operation.setOperationDate(new Date());
        operation.setDescription(description);
        operationRepository.save(operation);
    }

    BankAccount findAccountById(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException("bank account not found"));
        return bankAccount;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = findAccountById(accountId);
        if(bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation operation=new AccountOperation();
        operation.setType(OperationType.DEBIT);
        saveOperation(amount,bankAccount,description,operation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = findAccountById(accountId);
        AccountOperation operation=new AccountOperation();
        operation.setType(OperationType.CREDIT);
        saveOperation(amount,bankAccount,description,operation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer from "+accountIdSource);
    }
    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccountDTO bankAccount = getBankAccount(accountId);
        Page<AccountOperation> accountOperations = operationRepository.findAccountOperationByAccount_Id(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        List<AccountOperationDTO> operationDTOS = accountOperations.getContent().stream().map(operation ->
                bankAccountMapper.fromAccountOperation(operation)).collect(Collectors.toList());
        accountHistoryDTO.setOperationDTOS(operationDTOS);
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setId(accountId);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }
}
