package ma.ebank.it.ebankingbackend.services;

import ma.ebank.it.ebankingbackend.dto.AccountOperationDTO;

import java.util.Collection;

public interface AccountOperationService {

    Collection<AccountOperationDTO> accountHistory(String accountId);
}
