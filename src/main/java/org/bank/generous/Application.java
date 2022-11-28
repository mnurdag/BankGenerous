package org.bank.generous;

import org.bank.generous.domain.account.infrastructure.AccountRepository;
import org.bank.generous.domain.shared.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    @Autowired
    AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    private void init() {
        Account account1 = new Account();
        account1.setAccountNumber("1234567891");
        account1.setBalance(new BigDecimal("1000"));
        accountRepository.save(account1);
        Account account2 = new Account();
        account2.setAccountNumber("1234567892");
        account2.setBalance(new BigDecimal("2000"));
        accountRepository.save(account2);
    }

}
