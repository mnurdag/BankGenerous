package org.bank.generous.domain.transfer.core.ports.incoming;

import org.bank.generous.domain.transfer.core.model.TransferCommand;
import org.bank.generous.domain.transfer.core.model.TransferResponse;

public interface TransferMoney {

    TransferResponse handle(TransferCommand command);

}
