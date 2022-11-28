package org.bank.generous.domain.transfer.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.bank.generous.domain.transfer.core.model.TransferResponse;
import org.bank.generous.domain.transfer.core.ports.incoming.TransferMoney;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class TransferFacade implements TransferMoney {

    TransferService transferService;

    @Override
    public TransferResponse handle(TransferCommand command) {
        return transferService.transferMoney(command);
    }

}
