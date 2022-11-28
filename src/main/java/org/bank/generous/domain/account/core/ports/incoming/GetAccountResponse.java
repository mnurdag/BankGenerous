package org.bank.generous.domain.account.core.ports.incoming;

import org.bank.generous.domain.account.core.model.AccountResponse;

public interface GetAccountResponse {

    AccountResponse handle(String accountNumber);

}
