package org.bank.generous.domain.transfer.infrastructure;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.transfer.core.model.MoneyIsBeingTransferredEvent;
import org.bank.generous.domain.transfer.core.ports.outgoing.TransferEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpringTransferEventPublisherAdapter implements TransferEventPublisher {

    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishMoneyIsBeingTransferredEvent(MoneyIsBeingTransferredEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
