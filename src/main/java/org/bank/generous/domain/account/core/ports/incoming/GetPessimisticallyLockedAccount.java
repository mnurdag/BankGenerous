package org.bank.generous.domain.account.core.ports.incoming;

import org.bank.generous.domain.shared.entity.Account;

public interface GetPessimisticallyLockedAccount {

    Account handle(String accountNumber);

}
