package ma.ebank.it.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import ma.ebank.it.ebankingbackend.dto.AccountHistoryDTO;
import ma.ebank.it.ebankingbackend.dto.BankAccountDTO;
import ma.ebank.it.ebankingbackend.dto.CurrentBankAccountDTO;
import ma.ebank.it.ebankingbackend.dto.SavingBankAccountDTO;
import ma.ebank.it.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping(path = "accounts")
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
        SavingBankAccountDTO savedBankAccount = bankAccountService.createSavingBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomerDTO().getId(),
                bankAccountDTO.getInterestRate(),null, null,null);
        return savedBankAccount;
    }

    @PostMapping(path = "/currentAccount")
    public CurrentBankAccountDTO createCurrentAccount(@RequestBody CurrentBankAccountDTO bankAccountDTO) throws CustomerNotFoundException {
        CurrentBankAccountDTO savedBankAccount = bankAccountService.createCurrentBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomerDTO().getId(),
                bankAccountDTO.getOverDraft(), null, null, null);
        return savedBankAccount;
    }

    @PutMapping(path = "/savingAccount/{accountId}")
    public SavingBankAccountDTO updateSavingAccount(@RequestBody SavingBankAccountDTO bankAccountDTO,
                                                    @PathVariable String accountId) throws CustomerNotFoundException {
        SavingBankAccountDTO savedBankAccount = bankAccountService.createSavingBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomerDTO().getId(),
                bankAccountDTO.getInterestRate(),accountId, bankAccountDTO.getCreatedAt(), bankAccountDTO.getStatus());
        return savedBankAccount;
    }

    @PutMapping(path = "/currentAccount/{accountId}")
    public CurrentBankAccountDTO updateCurrentAccount(@RequestBody CurrentBankAccountDTO bankAccountDTO,
                                                      @PathVariable String accountId) throws CustomerNotFoundException {
        CurrentBankAccountDTO savedBankAccount = bankAccountService.createCurrentBankAccount(bankAccountDTO.getBalance(), bankAccountDTO.getCustomerDTO().getId(),
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
}
