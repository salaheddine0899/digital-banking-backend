package ma.ebank.it.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import ma.ebank.it.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.ebank.it.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.model.dto.*;
import ma.ebank.it.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "accounts")
@CrossOrigin("*")
public class BankAccountController {
    private BankAccountService bankAccountService;

    @GetMapping(path ={"","/"} )
    public Collection<BankAccountDTO> bankAccountList(){
        Collection<BankAccountDTO> bankAccountDTOS = bankAccountService.bankAccountList();
        return bankAccountDTOS;
    }

    @GetMapping(path = "/{id}")
    public BankAccountDTO getBankAccount(@PathVariable(name = "id") String accountId) throws BankAccountNotFoundException {
        BankAccountDTO bankAccountDTO=bankAccountService.getBankAccount(accountId);
        return bankAccountDTO;
    }

    @PostMapping(path = "/savingAccount")
    public SavingBankAccountDTO createSavingAccount(@RequestBody SavingBankAccountDTO bankAccountDTO) throws CustomerNotFoundException {
        SavingBankAccountDTO savedBankAccount = bankAccountService.createSavingBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomer().getId(),
                bankAccountDTO.getInterestRate(),null, null,null);
        return savedBankAccount;
    }

    @PostMapping(path = "/currentAccount")
    public CurrentBankAccountDTO createCurrentAccount(@RequestBody CurrentBankAccountDTO bankAccountDTO) throws CustomerNotFoundException {
        CurrentBankAccountDTO savedBankAccount = bankAccountService.createCurrentBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomer().getId(),
                bankAccountDTO.getOverDraft(), null, null, null);
        return savedBankAccount;
    }

    @PutMapping(path = "/savingAccount/{accountId}")
    public SavingBankAccountDTO updateSavingAccount(@RequestBody SavingBankAccountDTO bankAccountDTO,
                                                    @PathVariable String accountId) throws CustomerNotFoundException {
        SavingBankAccountDTO savedBankAccount = bankAccountService.createSavingBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomer().getId(),
                bankAccountDTO.getInterestRate(),accountId, bankAccountDTO.getCreatedAt(), bankAccountDTO.getStatus());
        return savedBankAccount;
    }

    @PutMapping(path = "/currentAccount/{accountId}")
    public CurrentBankAccountDTO updateCurrentAccount(@RequestBody CurrentBankAccountDTO bankAccountDTO,
                                                      @PathVariable String accountId) throws CustomerNotFoundException {
        CurrentBankAccountDTO savedBankAccount = bankAccountService.createCurrentBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomer().getId(),
                bankAccountDTO.getOverDraft(), accountId, bankAccountDTO.getCreatedAt(), bankAccountDTO.getStatus());
        return savedBankAccount;
    }
    @GetMapping(path = "{accountId}/operations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name = "page",defaultValue = "0") int page,
                                               @RequestParam(name = "size",defaultValue = "5") int size) throws BankAccountNotFoundException {
        AccountHistoryDTO accountHistory = bankAccountService.getAccountHistory(accountId, page, size);
        return accountHistory;
    }

    @PostMapping(path = "/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping(path = "/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping(path = "/transfer")
    public TransferDTO transfer(@RequestBody TransferDTO transferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(transferDTO.getAccountIdSource(),transferDTO.getAccountIdDestination(),transferDTO.getAmount());
        return transferDTO;
    }
}
