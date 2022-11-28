package org.bank.generous.domain.transfer.core.ports.outgoing;

import org.bank.generous.domain.transfer.core.model.MoneyIsBeingTransferredEvent;

public interface TransferEventPublisher {
    void publishMoneyIsBeingTransferredEvent(MoneyIsBeingTransferredEvent event);
}
