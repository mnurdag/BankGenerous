package org.bank.generous.domain.transfer.infrastructure;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.account.application.AccountDomainServer;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.transfer.core.ports.outgoing.DomainConnector;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomainConnectorImpl implements DomainConnector {

    AccountDomainServer accountDomainServer;

    @Override
    public Account getAccount(String accountNumber) {
        return accountDomainServer.getAccount(accountNumber);
    }

}
