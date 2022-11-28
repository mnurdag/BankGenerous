package org.bank.generous.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bank.generous.TestApplication;
import org.bank.generous.domain.transfer.application.TransferController;
import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.bank.generous.domain.transfer.core.model.TransferResponse;
import org.bank.generous.domain.transfer.core.ports.incoming.TransferMoney;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@WebMvcTest(TransferController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @NonFinal
    @MockBean
    TransferMoney transferMoney;

    @Test
    public void transferMoney_sendAccountNumberWith8Characters_shouldReturnErrorResponse() throws Exception {
        String sourceAccountNumber = "12345678";
        String destinationAccountNumber = "1234567890";
        BigDecimal amount = BigDecimal.TEN;

        TransferCommand command = new TransferCommand();
        command.setSourceAccountNumber(sourceAccountNumber);
        command.setDestinationAccountNumber(destinationAccountNumber);
        command.setAmount(amount);

        String requestBody = objectMapper.writeValueAsString(command);

        mockMvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Failed"));
    }

    @Test
    public void transferMoney_sendAmountAsZero_shouldReturnErrorResponse() throws Exception {
        String sourceAccountNumber = "1234567890";
        String destinationAccountNumber = "1234567890";
        BigDecimal amount = BigDecimal.ZERO;

        TransferCommand command = new TransferCommand();
        command.setSourceAccountNumber(sourceAccountNumber);
        command.setDestinationAccountNumber(destinationAccountNumber);
        command.setAmount(amount);

        String requestBody = objectMapper.writeValueAsString(command);

        mockMvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Failed"));
    }

    @Test
    public void transferMoney_sendAmountLessThanZero_shouldReturnErrorResponse() throws Exception {
        String sourceAccountNumber = "1234567890";
        String destinationAccountNumber = "1234567890";
        BigDecimal amount = new BigDecimal("-1000");

        TransferCommand command = new TransferCommand();
        command.setSourceAccountNumber(sourceAccountNumber);
        command.setDestinationAccountNumber(destinationAccountNumber);
        command.setAmount(amount);

        String requestBody = objectMapper.writeValueAsString(command);

        mockMvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Failed"));
    }

    @Test
    public void transferMoney_shouldReturnExpectedResponse() throws Exception {
        String sourceAccountNumber = "1234567890";
        String destinationAccountNumber = "1234567890";
        BigDecimal amount = new BigDecimal("1000");

        TransferResponse response = new TransferResponse();
        response.setTransactionId(1L);
        response.setSourceAccountNumber(sourceAccountNumber);
        response.setDestinationAccountNumber(destinationAccountNumber);
        response.setAmount(amount);

        doReturn(response).when(transferMoney).handle(any(TransferCommand.class));

        TransferCommand command = new TransferCommand();
        command.setSourceAccountNumber(sourceAccountNumber);
        command.setDestinationAccountNumber(destinationAccountNumber);
        command.setAmount(amount);

        String requestBody = objectMapper.writeValueAsString(command);

        mockMvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(response.getTransactionId()))
                .andExpect(jsonPath("$.sourceAccountNumber").value(response.getSourceAccountNumber()))
                .andExpect(jsonPath("$.destinationAccountNumber").value(response.getDestinationAccountNumber()))
                .andExpect(jsonPath("$.amount").value(response.getAmount()));
    }

}
