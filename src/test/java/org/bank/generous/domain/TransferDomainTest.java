package org.bank.generous.domain;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bank.generous.BaseTest;
import org.bank.generous.domain.account.core.AccountService;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.bank.generous.domain.transfer.core.ports.incoming.TransferMoney;
import org.bank.generous.domain.transfer.infrastructure.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDomainTest extends BaseTest {

    @Autowired
    TransferMoney transferMoney;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void transferMoney_shouldChangeAccountBalancesAndShouldCreateTransaction() {
        String sourceAccountNumber = "1358270981";
        String destinationAccountNumber = "7461839470";
        BigDecimal amount = new BigDecimal("100");
        TransferCommand command = new TransferCommand();
        command.setSourceAccountNumber(sourceAccountNumber);
        command.setDestinationAccountNumber(destinationAccountNumber);
        command.setAmount(amount);

        Account sourceAccount = accountService.findByAccountNumber(sourceAccountNumber);
        Account destinationAccount = accountService.findByAccountNumber(destinationAccountNumber);
        BigDecimal sourceInitialBalance = sourceAccount.getBalance();
        BigDecimal destinationInitialBalance = destinationAccount.getBalance();
        long initialTransactionCount = transactionRepository.count();

        transferMoney.handle(command);

        BigDecimal sourceExpectedBalance = sourceInitialBalance.subtract(amount);
        BigDecimal sourceActualBalance = sourceAccount.getBalance();
        assertEquals(sourceExpectedBalance, sourceActualBalance);
        BigDecimal destinationExpectedBalance = destinationInitialBalance.add(amount);
        BigDecimal destinationActualBalance = destinationAccount.getBalance();
        assertEquals(destinationExpectedBalance, destinationActualBalance);
        long expectedTransactionCount = initialTransactionCount + 1;
        long actualTransactionCount = transactionRepository.count();
        assertEquals(expectedTransactionCount, actualTransactionCount);
    }

}
