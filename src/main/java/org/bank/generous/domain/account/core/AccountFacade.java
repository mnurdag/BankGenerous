package org.bank.generous.domain.account.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.account.core.model.AccountResponse;
import org.bank.generous.domain.account.core.model.TryChangingAccountBalancesCommand;
import org.bank.generous.domain.account.core.ports.incoming.GetAccountResponse;
import org.bank.generous.domain.account.core.ports.incoming.TryChangingAccountBalances;
import org.bank.generous.domain.shared.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class AccountFacade implements GetAccountResponse, TryChangingAccountBalances {

    AccountService accountService;
    AccountResponseMapper accountResponseMapper;

    @Override
    public AccountResponse handle(String accountNumber) {
        Account account = accountService.findByAccountNumber(accountNumber);
        return accountResponseMapper.map(account);
    }

    @Override
    public void handle(TryChangingAccountBalancesCommand command) {
        accountService.tryChangingAccountBalances(command);
    }

}
