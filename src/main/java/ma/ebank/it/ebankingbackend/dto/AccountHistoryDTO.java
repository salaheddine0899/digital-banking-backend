package ma.ebank.it.ebankingbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter @Setter
public class AccountHistoryDTO {
    private String id;
    private double balance;
    private int currentPage;
    private int totalPages;
    private int PageSize;
    private Collection<AccountOperationDTO> operations;
}
