package org.bank.generous.domain.transfer.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.shared.entity.Account;
import org.bank.generous.domain.transfer.core.model.MoneyIsBeingTransferredEvent;
import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.bank.generous.domain.transfer.core.model.TransferResponse;
import org.bank.generous.domain.transfer.core.ports.outgoing.DomainConnector;
import org.bank.generous.domain.transfer.core.ports.outgoing.TransferEventPublisher;
import org.bank.generous.domain.transfer.infrastructure.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransferService {

    DomainConnector domainConnector;
    TransactionService transactionService;
    TransferEventPublisher transferEventPublisher;
    TransferResponseMapper transferResponseMapper;

    public TransferResponse transferMoney(TransferCommand command) {
        Transaction transaction = tryTransferAndReturnTransactionOnSuccess(command);
        return transferResponseMapper.map(transaction);
    }

    private Transaction tryTransferAndReturnTransactionOnSuccess(TransferCommand command) {
        String sourceAccountNumber = command.getSourceAccountNumber();
        String destinationAccountNumber = command.getDestinationAccountNumber();
        Account sourceAccount = domainConnector.getAccount(sourceAccountNumber);
        Account destinationAccount = domainConnector.getAccount(destinationAccountNumber);
        BigDecimal amount = command.getAmount();
        MoneyIsBeingTransferredEvent event = new MoneyIsBeingTransferredEvent(sourceAccount, destinationAccount,
                amount);
        transferEventPublisher.publishMoneyIsBeingTransferredEvent(event);
        return transactionService.create(sourceAccount, destinationAccount, amount);
    }

}
