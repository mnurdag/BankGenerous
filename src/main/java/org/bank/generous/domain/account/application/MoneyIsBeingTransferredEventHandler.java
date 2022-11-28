package org.bank.generous.domain.account.application;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.account.core.model.TryChangingAccountBalancesCommand;
import org.bank.generous.domain.account.core.ports.incoming.TryChangingAccountBalances;
import org.bank.generous.domain.transfer.core.model.MoneyIsBeingTransferredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MoneyIsBeingTransferredEventHandler {

    TryChangingAccountBalances tryChangingAccountBalances;

    @EventListener
    void handle(MoneyIsBeingTransferredEvent event) {
        TryChangingAccountBalancesCommand command = new TryChangingAccountBalancesCommand(event.getSourceAccount(),
                event.getDestinationAccount(), event.getAmount());
        tryChangingAccountBalances.handle(command);
    }

}
