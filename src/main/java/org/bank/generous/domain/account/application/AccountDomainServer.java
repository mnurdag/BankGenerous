package org.bank.generous.domain.account.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.account.core.ports.incoming.GetPessimisticallyLockedAccount;
import org.bank.generous.domain.shared.entity.Account;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountDomainServer {

    GetPessimisticallyLockedAccount getPessimisticallyLockedAccount;

    public Account getAccount(String accountNumber) {
        return getPessimisticallyLockedAccount.handle(accountNumber);
    }

}
