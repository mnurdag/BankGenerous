package org.bank.generous.domain.transfer.core;

import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.transfer.infrastructure.Transaction;

import java.math.BigDecimal;

public interface TransactionService {

    Transaction create(Account sourceAccount, Account destinationAccount, BigDecimal amount);

}
