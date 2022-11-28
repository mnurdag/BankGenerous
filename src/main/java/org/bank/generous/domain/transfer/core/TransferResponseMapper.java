package org.bank.generous.domain.transfer.core;

import org.bank.generous.domain.transfer.core.model.TransferResponse;
import org.bank.generous.domain.transfer.infrastructure.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransferResponseMapper {

    public TransferResponse map(Transaction transaction) {
        TransferResponse response = new TransferResponse();
        response.setTransactionId(transaction.getId());
        response.setSourceAccountNumber(transaction.getSourceAccount().getAccountNumber());
        response.setDestinationAccountNumber(transaction.getDestinationAccount().getAccountNumber());
        response.setAmount(transaction.getAmount());
        response.setTransactionTime(transaction.getTransactionTime());
        return response;
    }

}
