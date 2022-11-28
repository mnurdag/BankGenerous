package org.bank.generous.domain.transfer.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.transfer.infrastructure.Transaction;
import org.bank.generous.domain.transfer.infrastructure.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionServiceImpl implements TransactionService {

    TransactionRepository transactionRepository;

    @Override
    public Transaction create(Account sourceAccount, Account destinationAccount, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        return transaction;
    }

}
