package ma.ebank.it.ebankingbackend;


import ma.ebank.it.ebankingbackend.bo.AccountOperation;
import ma.ebank.it.ebankingbackend.bo.Customer;
import ma.ebank.it.ebankingbackend.dao.AccountOperationRepository;
import ma.ebank.it.ebankingbackend.dao.BankAccountRepository;
import ma.ebank.it.ebankingbackend.dao.CustomerRepository;
import ma.ebank.it.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.ebank.it.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.ebank.it.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.ebank.it.ebankingbackend.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountService bankAccountService, CustomerRepository customerRepository,
							BankAccountRepository accountRepository, AccountOperationRepository operationRepository){
		return args -> {
		};
	}
}
