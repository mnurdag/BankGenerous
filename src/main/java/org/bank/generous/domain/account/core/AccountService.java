package org.bank.generous.domain.account.core;

import org.bank.generous.domain.account.core.model.TryChangingAccountBalancesCommand;
import org.bank.generous.domain.shared.entity.Account;

public interface AccountService {

    Account findByAccountNumber(String accountNumber);

    void tryChangingAccountBalances(TryChangingAccountBalancesCommand changingAccountBalancesCommand);

}
