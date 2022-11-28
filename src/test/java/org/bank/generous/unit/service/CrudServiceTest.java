package org.bank.generous.unit.service;

import org.bank.generous.domain.account.core.AccountFacade;
import org.bank.generous.domain.account.core.AccountResponseMapper;
import org.bank.generous.domain.account.core.AccountService;
import org.bank.generous.domain.account.core.model.AccountResponse;
import org.bank.generous.domain.shared.entity.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CrudServiceTest {

    @Mock
    AccountService accountService;
    @Mock
    AccountResponseMapper accountResponseMapper;
    @InjectMocks
    AccountFacade accountFacade;

    @Test
    public void readAccount_shouldReturnExpectedAccount() {
        String accountNumber = "1358270981";
        BigDecimal balance = new BigDecimal("1000");

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);

        AccountResponse expectedResponse = new AccountResponse();
        expectedResponse.setAccountNumber(accountNumber);
        expectedResponse.setBalance(balance);

        given(accountService.findByAccountNumber(accountNumber)).willReturn(account);
        given(accountResponseMapper.map(account)).willReturn(expectedResponse);

        AccountResponse actualResponse =  accountFacade.handle(accountNumber);

        assertEquals(expectedResponse.getAccountNumber(), actualResponse.getAccountNumber());
        assertEquals(expectedResponse.getBalance(), actualResponse.getBalance());
    }

}
