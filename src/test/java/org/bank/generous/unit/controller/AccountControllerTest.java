package org.bank.generous.unit.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bank.generous.TestApplication;
import org.bank.generous.domain.account.application.AccountController;
import org.bank.generous.domain.account.core.model.AccountResponse;
import org.bank.generous.domain.account.core.ports.incoming.GetAccountResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@WebMvcTest(AccountController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GetAccountResponse getAccountResponse;

    @Test
    public void readAccount_sendAccountNumberWith5Characters_shouldThrowException() throws Exception {
        String accountNumber = "12345";
        BigDecimal balance = new BigDecimal("1000");

        AccountResponse response = new AccountResponse();
        response.setAccountNumber(accountNumber);
        response.setBalance(balance);

        when(getAccountResponse.handle(any(String.class))).thenReturn(response);

        mockMvc.perform(get("/account/" + accountNumber))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Failed"));

    }

    @Test
    public void readAccount_shouldReturnExpectedResponse() throws Exception {
        String accountNumber = "1234567890";
        BigDecimal balance = new BigDecimal("1000");

        AccountResponse response = new AccountResponse();
        response.setAccountNumber(accountNumber);
        response.setBalance(balance);

        when(getAccountResponse.handle(any(String.class))).thenReturn(response);

        mockMvc.perform(get("/account/" + accountNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(accountNumber))
                .andExpect(jsonPath("$.balance").value(balance));

    }

}
