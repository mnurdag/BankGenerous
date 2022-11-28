package org.bank.generous.unit.repository;

import org.bank.generous.TestApplication;
import org.bank.generous.domain.account.core.AccountService;
import org.bank.generous.domain.account.infrastructure.AccountRepository;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.transfer.core.TransferFacade;
import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AccountRepositoryTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferFacade transferFacade;

    @DisplayName("Concurrency test for money transfer")
    @Test
    public void transferMoney_accountBalancesSouldBeCorrect() {
        String sourceAccountNumber = "1358270981";
        String destinationAccountNumber = "7461839470";
        BigDecimal amount = BigDecimal.TEN;
        long transactionCount = 1000;

        Account sourceAccountInitial = accountService.findByAccountNumber(sourceAccountNumber);
        Account destinationAccountInitial = accountService.findByAccountNumber(destinationAccountNumber);
        BigDecimal sourceInitialBalance = sourceAccountInitial.getBalance();
        BigDecimal destinationInitialBalance = destinationAccountInitial.getBalance();

        TransferCommand command = new TransferCommand();
        command.setSourceAccountNumber(sourceAccountNumber);
        command.setDestinationAccountNumber(destinationAccountNumber);
        command.setAmount(amount);

        IntStream.range(0, (int) transactionCount).parallel().forEach(value -> {
            transferFacade.handle(command);
        });

        Account sourceAccountAfter = accountService.findByAccountNumber(sourceAccountNumber);
        Account destinationAccountAfter = accountService.findByAccountNumber(destinationAccountNumber);
        BigDecimal transactionCountAsDecimal = BigDecimal.valueOf(transactionCount);
        BigDecimal sourceExpectedBalance = sourceInitialBalance.subtract(amount.multiply(transactionCountAsDecimal));
        BigDecimal destinationExpectedBalance = destinationInitialBalance.add(amount.multiply(transactionCountAsDecimal));

        assertEquals(sourceExpectedBalance, sourceAccountAfter.getBalance());
        assertEquals(destinationExpectedBalance, destinationAccountAfter.getBalance());
    }

}
