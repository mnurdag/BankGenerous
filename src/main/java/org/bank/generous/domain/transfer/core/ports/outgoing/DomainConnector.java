package org.bank.generous.domain.transfer.core.ports.outgoing;


import org.bank.generous.domain.shared.entity.Account;

public interface DomainConnector {

    Account getAccount(String accountNumber);

}
