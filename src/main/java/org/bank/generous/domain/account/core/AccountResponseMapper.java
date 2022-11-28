package org.bank.generous.domain.account.core;

import org.bank.generous.domain.account.core.model.AccountResponse;
import org.bank.generous.domain.shared.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountResponseMapper {

    public AccountResponse map(Account account) {
        AccountResponse response = new AccountResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setBalance(account.getBalance());
        return response;
    }

}
