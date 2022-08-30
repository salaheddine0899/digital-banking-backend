package ma.ebank.it.ebankingbackend.services;

import lombok.AllArgsConstructor;
import ma.ebank.it.ebankingbackend.bo.AccountOperation;
import ma.ebank.it.ebankingbackend.dao.AccountOperationRepository;
import ma.ebank.it.ebankingbackend.dto.*;
import ma.ebank.it.ebankingbackend.mappers.BankAccountMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountOperationServiceImpl implements AccountOperationService{
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapper mapper;
    @Override
    public Collection<AccountOperationDTO> accountHistory(String accountId){
        Collection<AccountOperation> accountOperations = accountOperationRepository.findAccountOperationByAccount_Id(accountId);
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.stream().map(operation -> mapper.fromAccountOperation(operation)).collect(Collectors.toList());
        return accountOperationDTOS;
    }
}
