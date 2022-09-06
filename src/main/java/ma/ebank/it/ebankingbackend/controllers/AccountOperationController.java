package ma.ebank.it.ebankingbackend.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ma.ebank.it.ebankingbackend.model.dto.AccountOperationDTO;
import ma.ebank.it.ebankingbackend.services.AccountOperationService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(path = "operations/")
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
public class AccountOperationController {
    private AccountOperationService operationService;
    @GetMapping(path = "byAccount/{accountId}")
    public Collection<AccountOperationDTO> accountHistory(@PathVariable String accountId){
        log.info("Operations history of "+accountId);
        return operationService.accountHistory(accountId);
    }
}
