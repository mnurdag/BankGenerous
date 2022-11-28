package org.bank.generous.domain.account.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.account.core.model.TryChangingAccountBalancesCommand;
import org.bank.generous.domain.account.core.ports.incoming.GetPessimisticallyLockedAccount;
import org.bank.generous.domain.account.infrastructure.AccountRepository;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.shared.exception.BankException;
import org.bank.generous.domain.shared.service.MessageService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService, GetPessimisticallyLockedAccount {

    AccountRepository accountRepository;
    MessageService messageService;

    @Override
    public Account handle(String accountNumber) {
        return accountRepository.findLockedAccountByAccountNumber(accountNumber).orElseThrow(() -> {
            String message = messageService.getMessage("account.by.accountnumber.not.found", accountNumber);
            return new BankException(message);
        });
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> {
            String message = messageService.getMessage("account.by.accountnumber.not.found", accountNumber);
            return new BankException(message);
        });
    }

    @Override
    public void tryChangingAccountBalances(TryChangingAccountBalancesCommand command) {
        Account sourceAccount = command.getSourceAccount();
        Account destinationAccount = command.getDestinationAccount();
        BigDecimal amount = command.getAmount();
        tryTransfer(sourceAccount, destinationAccount, amount);
    }

    private void tryTransfer(Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        if(sourceAccount.hasEnoughBalanceForAmount(amount)) {
            makeTransfer(sourceAccount, destinationAccount, amount);
        } else {
            String message = messageService.getMessage("account.does.not.have.enough.balance",
                    sourceAccount.getAccountNumber(), amount.toString());
            throw new BankException(message);
        }
    }

    private void makeTransfer(Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        substractAmountFromSourceAccountAndGetAccount(sourceAccount, amount);
        addAmountToDestinationAccountAndGetAccount(destinationAccount, amount);
    }

    private void substractAmountFromSourceAccountAndGetAccount(Account sourceAccount, BigDecimal amount) {
        BigDecimal balance = sourceAccount.getBalance();
        sourceAccount.setBalance(balance.subtract(amount));
    }

    private void addAmountToDestinationAccountAndGetAccount(Account destinationAccount, BigDecimal amount) {
        BigDecimal balance = destinationAccount.getBalance();
        destinationAccount.setBalance(balance.add(amount));
    }
}
