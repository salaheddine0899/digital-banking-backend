package ma.ebank.it.ebankingbackend.services;

import ma.ebank.it.ebankingbackend.dto.AccountHistoryDTO;
import ma.ebank.it.ebankingbackend.dto.BankAccountDTO;
import ma.ebank.it.ebankingbackend.dto.CurrentBankAccountDTO;
import ma.ebank.it.ebankingbackend.dto.SavingBankAccountDTO;
import ma.ebank.it.ebankingbackend.enums.AccountStatus;
import ma.ebank.it.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.ebank.it.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;

import java.util.Collection;
import java.util.Date;


public interface BankAccountService {


    SavingBankAccountDTO createSavingBankAccount(double initialBalance, Long customerId, double interestRate, String accountId, Date date, AccountStatus accountStatus) throws CustomerNotFoundException;

    CurrentBankAccountDTO createCurrentBankAccount(double initialBalance, Long customerId, double overdraft, String accountId, Date date, AccountStatus accountStatus) throws CustomerNotFoundException;

    BankAccountDTO getBankAccount(String id) throws BankAccountNotFoundException;

    Collection<BankAccountDTO> bankAccountList();

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId,double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource,String accountIdDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
