package org.bank.generous.domain.transfer.core.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bank.generous.domain.shared.entity.Account;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoneyIsBeingTransferredEvent {
    Account sourceAccount;
    Account destinationAccount;
    BigDecimal amount;
}
