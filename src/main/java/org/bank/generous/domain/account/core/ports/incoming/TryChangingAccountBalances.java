package org.bank.generous.domain.account.core.ports.incoming;

import org.bank.generous.domain.account.core.model.TryChangingAccountBalancesCommand;

public interface TryChangingAccountBalances {

    void handle(TryChangingAccountBalancesCommand changingAccountBalancesCommand);

}
