package ma.ebank.it.ebankingbackend;


import ma.ebank.it.ebankingbackend.model.dao.AccountOperationRepository;
import ma.ebank.it.ebankingbackend.model.dao.BankAccountRepository;
import ma.ebank.it.ebankingbackend.model.dto.CustomerAccountsDTO;
import ma.ebank.it.ebankingbackend.services.BankAccountService;
import ma.ebank.it.ebankingbackend.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountService bankAccountService, CustomerService customerService,
							BankAccountRepository accountRepository, AccountOperationRepository operationRepository){
		return args -> {
			CustomerAccountsDTO customerAccountsDTO = customerService.customerAccounts(Long.valueOf(1),0,1);
			customerAccountsDTO.getAccounts().forEach(acc->{
				System.out.println(acc);
			});

		};
	}
}
